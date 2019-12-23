package com.example.careering;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ApplicantsAdapter extends ArrayAdapter<String> {

    private Context aContext;
    int aResource;
    int size;


    public ApplicantsAdapter(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        aContext = context;
        aResource = resource;
        size = objects.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String applicantName = "";

        if(position < size)
        {
            applicantName = getItem(position);
        }

        LayoutInflater inflater = LayoutInflater.from(aContext);
        convertView = inflater.inflate(aResource, parent, false);
        TextView applicantNameTV = convertView.findViewById(R.id.applicantNameTextView);
        applicantNameTV.setText(applicantName);

        return convertView;
    }
}
