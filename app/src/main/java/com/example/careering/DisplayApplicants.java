package com.example.careering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayApplicants extends Base {

    ArrayList<String> namesOfApplicants;
    ListView applicantsListView;
    private ApplicantsAdapter applicantsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_applicants);

        namesOfApplicants = new ArrayList<>();
        namesOfApplicants = getIntent().getStringArrayListExtra("ListOfNamesApplicants");

        applicantsListView = findViewById(R.id.listviewApplicants);
        applicantsAdapter = new ApplicantsAdapter(DisplayApplicants.this, R.layout.adapter_applicants, namesOfApplicants);
        applicantsListView.setAdapter(applicantsAdapter);

        applicantsListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int pos, long l) {
                if (!isConnected(DisplayApplicants.this))
                    Toast.makeText(getApplicationContext(), "No Internet Connection...\nPlease, check your internet connection", Toast.LENGTH_LONG).show();
                else {
                    if (pos < namesOfApplicants.size()) {
                        Log.i("NAME: ", namesOfApplicants.get(pos));
                        Intent i = new Intent(getApplicationContext(), ApplicantProfileScreen.class);
                        i.putExtra("name", namesOfApplicants.get(pos));
                        startActivity(i);
                    }

                }
            }
        });
    }

}
