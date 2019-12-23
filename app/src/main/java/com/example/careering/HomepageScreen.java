package com.example.careering;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class HomepageScreen extends AppCompatActivity implements AdapterForSearch.ItemClickListener {

    private DrawerLayout mDrawerLayout;
    private ParseUser user;
    private SearchView searchView;
    private RecyclerView searchListRV;
    private ArrayList<Post> posts;
    private AdapterForSearch searchAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_screen);

        user = ParseUser.getCurrentUser();
        posts = new ArrayList<>();
        mDrawerLayout = findViewById(R.id.drawer_layout);
        searchListRV = findViewById(R.id.postListInSearch);


        initializeSearchView();

        getItemsFromDatabase();


        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        TextView navTitle = (TextView) findViewById(R.id.navHeaderTitle);
                        navTitle.setText("Hər vaxtın xeyir, " + user.getUsername());
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        if (menuItem.getTitle().toString().matches("My Profile Info")) {
                            Intent intent = new Intent(getApplicationContext(), ProfileScreen.class);
                            startActivity(intent);
                        } else if (menuItem.getTitle().toString().matches("Create a Post")) {
                            Intent intent = new Intent(getApplicationContext(), CreatePostScreen.class);
                            startActivity(intent);
                        } else if (menuItem.getTitle().toString().matches("Log Out")) {
                            if (user != null) {
                                Toast.makeText(getApplicationContext(), "Logged out", Toast.LENGTH_SHORT).show();
                                ParseUser.logOut();
                                Intent intent = new Intent(getApplicationContext(), LoginScreen.class);
                                startActivity(intent);
                            }
                        }
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();
                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        return true;
                    }
                });

    }

    private void initializeSearchView() {
        searchView = findViewById(R.id.searchInHome);
        searchView.setQueryHint("Search a post");
        searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getData(newText);
                return false;
            }
        });
    }

    private void getData(String query) {

        ArrayList<Post> output = new ArrayList<>();
        ArrayList<Post> filteredOutput = new ArrayList<>();

        for (int i = 0; i < posts.size(); i++)
            output.add(posts.get(i));

        if (searchView != null) {
            for (Post item : output) {
                if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredOutput.add(item);
                }
            }
        } else
            filteredOutput = output;

        searchAdapter = new AdapterForSearch(HomepageScreen.this, filteredOutput);
        searchAdapter.setClickListener(HomepageScreen.this);
        searchListRV.setAdapter(searchAdapter);
    }

    private void getItemsFromDatabase() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    for (ParseObject object : list) {
                        Post post = new Post();
                        post.setName(object.get("postTitle").toString());
                        post.setCompany(object.get("postCompany").toString());
                        post.setPublisherName(object.get("postName").toString());
                        post.setDescription(object.get("postDescription").toString());
                        post.setUserID(object.get("postUserID").toString());

                        posts.add(post);

                        searchListRV = findViewById(R.id.postListInSearch);
                        searchListRV.setLayoutManager(new LinearLayoutManager(HomepageScreen.this));
                        searchAdapter = new AdapterForSearch(HomepageScreen.this, posts);
                        searchAdapter.setClickListener(HomepageScreen.this);
                        searchListRV.setAdapter(searchAdapter);
                    }

                } else {
                    // something went wrong
                }
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(getApplicationContext().getResources().getString(R.string.word_quit))
                .setMessage(getApplicationContext().getResources().getString(R.string.word_do_you_want_to_quit))
                .setPositiveButton(getApplicationContext().getResources().getString(R.string.word_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        moveTaskToBack(true);
                    }
                })
                .setNegativeButton(getApplicationContext().getResources().getString(R.string.word_cancel), null)
                .show();
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
