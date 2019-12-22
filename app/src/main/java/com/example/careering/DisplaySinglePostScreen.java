package com.example.careering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplaySinglePostScreen extends AppCompatActivity {

    TextView postTitle;
    TextView postDescription;
    Button applyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_single_post_screen);

        postTitle = findViewById(R.id.postTitle);
        postDescription = findViewById(R.id.postDescription);
        applyButton = findViewById(R.id.applyButton);
    }

    public void apply(View v) {
        applyButton.setActivated(false);



        Intent intent = new Intent(getApplicationContext(), DisplayTestScreen.class);
        startActivity(intent);
    }
}
