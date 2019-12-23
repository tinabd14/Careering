package com.example.careering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class DisplayYourPosts extends AppCompatActivity implements  AdapterForSearch.ItemClickListener{

    private ParseUser user;
    private RecyclerView searchListRV;
    private ArrayList<Post> posts;
    private AdapterForSearch searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_your_posts);

        user = ParseUser.getCurrentUser();
        posts = new ArrayList<>();
        searchListRV = findViewById(R.id.yourPostsList);

        getItemsFromDatabase();

    }
    private void getItemsFromDatabase() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    for (ParseObject object : list) {
                        if(object.get("postUserID").equals(ParseUser.getCurrentUser().getObjectId())) {
                            Post post = new Post();
                            post.setName(object.get("postTitle").toString());
                            post.setCompany(object.get("postCompany").toString());
                            post.setPublisherName(object.get("postName").toString());
                            post.setDescription(object.get("postDescription").toString());
                            post.setUserID(object.get("postUserID").toString());
                            post.applicants.addAll(object.<String>getList("applicants"));

                            posts.add(post);

                            searchListRV = findViewById(R.id.yourPostsList);
                            searchListRV.setLayoutManager(new LinearLayoutManager(DisplayYourPosts.this));
                            searchAdapter = new AdapterForSearch(DisplayYourPosts.this, posts);
                            searchAdapter.setClickListener(DisplayYourPosts.this);
                            searchListRV.setAdapter(searchAdapter);
                        }
                    }
                } else {
                    // something went wrong
                }
            }
        });


    }

    @Override
    public void onItemClick(View view, int position) {
        searchAdapter.getItem(position);
        ArrayList<String> namesOfApplicants = new ArrayList<>();
        Intent intent = new Intent(getApplicationContext(), DisplayApplicants.class);

        for(String str : searchAdapter.getItem(position).applicants) {
            namesOfApplicants.add(str);
        }
        intent.putStringArrayListExtra("ListOfNamesApplicants",namesOfApplicants);
        startActivity(intent);
    }
}
