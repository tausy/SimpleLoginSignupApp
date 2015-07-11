package com.athvillar.tausy.simpleloginsignupapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


public class LoginActivity extends Activity{

    private EditText username;
    private EditText password;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //get view data
        username = (EditText) findViewById(R.id.editTextUsername);
        password = (EditText) findViewById(R.id.editTextPassword);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);

        //attaching on click listener with login button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }


    private void login() {

        String username, password;

        username = this.username.getText().toString();
        password = this.password.getText().toString();


        boolean isDataProper = validateData(username, password);

        if (isDataProper) {

            // setup and show progress dialog
            progress = new ProgressDialog(LoginActivity.this);
            progress.setMessage("Logging in ....");
            progress.show();

            //parse login in background
            ParseUser.logInInBackground(username,password, new LogInCallback() {
                @Override
                public void done(ParseUser parseUser, ParseException e) {
                    //dismiss the progress dialog
                    progress.dismiss();

                    //check if there is any parse exception
                    if(e != null){
                        Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                    else{

                        //Start a Intent for the Dispatch Activity
                        Intent intent = new Intent(LoginActivity.this, DispatchActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }
            });


        }
    }

        // data validation
    private boolean validateData(String username, String password){

        boolean isValidated = true;
        String msg="";
        if(username.equals("") ){
            isValidated = false;
            msg = "Username field cannot be left blank!!";
        }
        else if(password.equals("")){
            isValidated = false;
            msg = "Password field cannot be left blank!!";
        }

        if(!msg.equals(""))
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_LONG).show();

        return isValidated;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
