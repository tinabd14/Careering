package com.example.careering;

import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.Parse;
import com.parse.ParseInstallation;

abstract class Base extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onStart() {
        super.onStart();
    }
    //To hide keyboard
    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.constraintLayout2)
        {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }

    }

    public void goToHomepage() {
        Intent intent = new Intent(getApplicationContext(), HomepageScreen.class);
        startActivity(intent);
    }

    public void InitializeParseServer() {
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
