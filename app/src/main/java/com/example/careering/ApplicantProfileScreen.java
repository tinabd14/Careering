package com.example.careering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ApplicantProfileScreen extends AppCompatActivity {

    TextView name;
    TextView profileInfo;
    TextView jobPosition;
    String applicantEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applicant_profile_screen);

        name = findViewById(R.id.nameTextApplicant);
        jobPosition = findViewById(R.id.positionApplicant);
        profileInfo = findViewById(R.id.InfoApplicant);
        applicantEmail = "";

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("name", getIntent().getStringExtra("name"));
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> list, ParseException e) {
                if (e == null) {
                    for (ParseUser user : list) {
                        name.setText(user.getString("name"));
                        jobPosition.setText(user.getString("jobPosition"));
                        profileInfo.setText(user.getString("profileInfo"));
                        applicantEmail = user.getString("email2");
                    }
                } else {
                    // something went wrong
                }
            }
        });

    }

    public void sendEmail(View view)
    {
        Log.i("EMAIL: ", applicantEmail);
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", applicantEmail, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Careering");
        startActivity(Intent.createChooser(emailIntent, "Send mail"));
    }
}
