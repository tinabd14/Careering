package com.example.careering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class DisplaySinglePostScreen extends AppCompatActivity {

    TextView postTitle;
    TextView postCompany;
    TextView postPublisher;
    TextView postDescription;
    Button applyButton;

    String postID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_single_post_screen);

        postTitle = findViewById(R.id.postTitle);
        postPublisher = findViewById(R.id.postPublisherName);
        postCompany = findViewById(R.id.postCompany);
        postDescription = findViewById(R.id.postDescription);

        applyButton = findViewById(R.id.applyButton);

        postTitle.setText(getIntent().getStringExtra("POSTNAME"));
        postCompany.setText(getIntent().getStringExtra("POSTCOMPANY"));
        postPublisher.setText(getIntent().getStringExtra("POSTPUBLISHERNAME"));
        postDescription.setText(getIntent().getStringExtra("POSTDESCRIPTION"));
        postID = getIntent().getStringExtra("POSTID");


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.whereEqualTo("objectId", postID);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    for (ParseObject object : list) {
                        if(object.getString("postUserID").equals(ParseUser.getCurrentUser().getObjectId()))
                        {
                            applyButton.setVisibility(View.INVISIBLE);
                        }
                    }

                } else {
                    // something went wrong
                }
            }
        });    }

    public void apply(View v) {
        applyButton.setActivated(false);
        final ArrayList<String> applicants = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.whereEqualTo("objectId", postID);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    for (ParseObject object : list) {
                        applicants.addAll(object.<String>getList("applicants"));
                        applicants.add(ParseUser.getCurrentUser().getString("name"));
                        object.put("applicants", applicants);
                        object.saveInBackground();
                    }

                } else {
                    // something went wrong
                }
            }
        });
        Toast.makeText(this, "You successfully applied", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), HomepageScreen.class);
        startActivity(intent);
    }
}
