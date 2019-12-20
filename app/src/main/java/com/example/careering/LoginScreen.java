package com.example.careering;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginScreen extends Base{

    EditText username;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        InitializeParseServer();

        username = findViewById(R.id.usernameLoginText);
        password = findViewById(R.id.passwordLoginText);
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout2);
        constraintLayout.setOnClickListener(this);
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
