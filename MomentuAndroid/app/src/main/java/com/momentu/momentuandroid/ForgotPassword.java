package com.momentu.momentuandroid;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.momentu.momentuandroid.Data.RestClient;

import java.io.IOException;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_1st_step_reset_password);
    }

    //On Click method to go to term and policy activity
    public void onClickFirstStep(View v){
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null :
                getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        TextView emailTextView = (TextView)findViewById(R.id.email_for_password_reset);
        String email = emailTextView.getText().toString();

        try {
            new RestClient().forgotPassword(email);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(ForgotPassword.this, "Check your email for a reset link", Toast.LENGTH_LONG).show();
    }
}
