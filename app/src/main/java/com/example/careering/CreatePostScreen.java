package com.example.careering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreatePostScreen extends AppCompatActivity {


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

    }

    public void createPost(View view) {
        if (postTitle.getText().toString().equals("") || postCompany.getText().toString().equals("") ||
                postPublisherName.getText().toString().equals("")) {
            Toast.makeText(this, "Please fill all fields before publishing", Toast.LENGTH_LONG).show();
        }
        else {

            //TODO : add the post to database
            Intent intent = new Intent(getApplicationContext(), HomepageScreen.class);
            startActivity(intent);
        }
    }
}
