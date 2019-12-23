package com.example.careering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplaySinglePostScreen extends AppCompatActivity {

    TextView postTitle;
    TextView postCompany;
    TextView postPublisher;
    TextView postDescription;
    Button applyButton;

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
    }

    public void apply(View v) {
        applyButton.setActivated(false);

        Toast.makeText(this, "You successfully applied", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), HomepageScreen.class);
        startActivity(intent);
    }
}
