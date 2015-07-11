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

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class SignupActivity extends Activity {

    private    EditText username;
    private    EditText password;
    private    EditText passwordAgain;
    private    Button signupBtn;
    private    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        //getting views info
        username = (EditText) findViewById(R.id.editTextUsername);
        password = (EditText) findViewById(R.id.editTextPassword);
        passwordAgain = (EditText) findViewById(R.id.editTextRetypePassword);
        signupBtn = (Button) findViewById(R.id.btnSignup);

        //attaching on click listener to the signup button
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });


    }

    private void signup(){
        String username, password, passwordAgain;

        username = this.username.getText().toString();
        password = this.password.getText().toString();
        passwordAgain = this.passwordAgain.getText().toString();

        boolean isDataProper = validateData(username, password, passwordAgain);

        if(isDataProper){

            // setup and show progress dialog
            progress = new ProgressDialog(SignupActivity.this);
            progress.setMessage("Logging in ....");
            progress.show();


            //Setup a new Parse User
            ParseUser user = new ParseUser();
            user.setUsername(username);
            user.setPassword(password);

            //call parse signup method
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {

                    //dismiss the progress dialog
                    progress.dismiss();

                    //check if there is any parse exception
                    if(e != null){
                        Toast.makeText(SignupActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                    else{

                        //Start a Intent for the Dispatch Activity
                        Intent intent = new Intent(SignupActivity.this, DispatchActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }
            });
        }
        else{
            return;
        }


    }


    // data validation
    private boolean validateData(String username, String password, String passwordAgain){

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
        else if(passwordAgain.equals("")){
            isValidated = false;
            msg = "Password retype field cannot be left blank!!";
        }
        else if(!password.equals(passwordAgain)) {
            msg = "Password not matched!!";
            isValidated = false;
        }
        if(!msg.equals(""))
        Toast.makeText(SignupActivity.this,msg,Toast.LENGTH_LONG).show();

        return isValidated;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
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
