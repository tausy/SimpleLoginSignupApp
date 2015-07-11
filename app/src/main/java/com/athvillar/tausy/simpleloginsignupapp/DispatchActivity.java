package com.athvillar.tausy.simpleloginsignupapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.ParseUser;

/**
 * Created by TAUSY on 7/12/2015.
 */
public class DispatchActivity extends Activity{


    public static final String EXTRA_MESSAGE = "com.athvillar.tausy.simpleloginsignupapp.MESSAGE";

    public DispatchActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if there is current user info
        ParseUser user = ParseUser.getCurrentUser();
        if (user != null) {
            // Start an intent for the logged in activity
            Intent intent = new Intent(DispatchActivity.this, MainActivity.class);
            intent.putExtra(EXTRA_MESSAGE,user.getUsername().toString());
            startActivity(intent);
        }
        else {
            // Start and intent for the logged out activity
            startActivity(new Intent(this, WelcomeActivity.class));
        }
    }

}
