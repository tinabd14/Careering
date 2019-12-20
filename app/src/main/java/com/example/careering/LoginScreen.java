package com.example.careering;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;


import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener{

    EditText username;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        InitializeParseServer();

        username = findViewById(R.id.usernameLoginText);
        password = findViewById(R.id.passwordLoginText);

        ConstraintLayout loginLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);
        loginLayout.setOnClickListener(this);
    }

    private void InitializeParseServer() {
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

    public void logIn(View view) {
        password.setError(null);
        ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    if(user.getBoolean("emailVerified"))
                    {
                        Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.word_welcome) + ", " + username.getText().toString() + "!", Toast.LENGTH_SHORT).show();
                        goToHomepage();
                    }
                    else
                    {
                        ParseUser.logOut();
                        Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.word_verif_email), Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), e.getMessage() + "\n" + getApplicationContext().getResources().getString(R.string.word_retry), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void signUp(View view) {
        Intent intent = new Intent(getApplicationContext(), SignUpScreen.class);
        startActivity(intent);
    }

    public void goToHomepage() {
        Intent intent = new Intent(getApplicationContext(), HomepageScreen.class);
        startActivity(intent);
    }


    //To hide keyboard
    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.constraintLayout)
        {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }

    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(getApplicationContext().getResources().getString(R.string.word_quit))
                .setMessage(getApplicationContext().getResources().getString(R.string.word_do_you_want_to_quit))
                .setPositiveButton(getApplicationContext().getResources().getString(R.string.word_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        moveTaskToBack(true);
                    }
                })
                .setNegativeButton(getApplicationContext().getResources().getString(R.string.word_cancel), null)
                .show();
    }
}
