package com.example.careering;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ApplicantProfileScreen extends AppCompatActivity {

    TextView name;
    TextView profileInfo;
    TextView jobPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applicant_profile_screen);

        name = findViewById(R.id.nameTextApplicant);
        jobPosition = findViewById(R.id.positionApplicant);
        profileInfo = findViewById(R.id.InfoApplicant);

    }
}
