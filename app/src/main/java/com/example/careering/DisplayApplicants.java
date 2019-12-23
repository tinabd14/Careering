package com.example.careering;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class DisplayApplicants extends AppCompatActivity {

    ArrayList<String> namesOfApplicants;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_applicants);

        namesOfApplicants = new ArrayList<>();
        namesOfApplicants = getIntent().getStringArrayListExtra("ListOfNamesApplicants");

        for(String str : namesOfApplicants) {

        }
    }
}
