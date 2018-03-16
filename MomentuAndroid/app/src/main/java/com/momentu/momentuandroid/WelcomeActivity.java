package com.momentu.momentuandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        /*
        TextView Fahad = (TextView) findViewById(R.id.text);
        Fahad.setText("Fahad Alharbi\n"+"Ibrahim Alawwad\n"+"Jerry Kobeszko\n"+"Zhen Chen");
        */
    }

    //On Click method to go to sign up activity
    public void onClickSignUp(View v){
        Intent signUp = new Intent(this, SignUpActivity.class);
        startActivity(signUp);
    }

    //On Click method to go to sign up activity
    public void onClickLogin(View v){
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
    }

    //On Click method to go to term and policy activity
    public void onClickTermAndPolicy(View v){
        Intent termAndPolicy = new Intent(this, TermAndPolicyActivity.class);
        startActivity(termAndPolicy);
    }
    //On Click method to go to term and policy activity
    public void onClickForgotPassword(View v){
        Intent forgotPassword = new Intent(this, ForgotPassword.class);
        startActivity(forgotPassword);
    }

}