package com.example.careering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CreatePostScreen extends Base {


    EditText postTitle;
    EditText postCompany;
    EditText postPublisherName;
    EditText postDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_post_screen);

        postTitle = findViewById(R.id.createPostTitle);
        postCompany = findViewById(R.id.createPostCompany);
        postPublisherName = findViewById(R.id.createPostPublisherName);
        postDescription = findViewById(R.id.createPostDescription);

        postPublisherName.setText(ParseUser.getCurrentUser().get("name").toString());

    }

    public void createPost(View view) {
        if (postTitle.getText().toString().equals("") || postCompany.getText().toString().equals("") ||
                postPublisherName.getText().toString().equals("")) {

            Toast.makeText(this, "Please fill all fields before publishing", Toast.LENGTH_LONG).show();
        }
        else if (!isConnected(CreatePostScreen.this))
            Toast.makeText(getApplicationContext(), "No Internet Connection...\nPlease, check your internet connection", Toast.LENGTH_LONG).show();
        else {

            ParseObject post = new ParseObject("Post");
            post.put("postTitle", postTitle.getText().toString());
            post.put("postName", postPublisherName.getText().toString());
            post.put("postCompany", postCompany.getText().toString());
            post.put("postDescription", postDescription.getText().toString());
            post.put("postUserID", ParseUser.getCurrentUser().getObjectId().toString());
            ArrayList<String> applicants = new ArrayList<>();
            post.put("applicants", applicants);

            post.saveInBackground();

            Toast.makeText(this, "The post published successfully", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplicationContext(), HomepageScreen.class);
            startActivity(intent);
        }
    }
}