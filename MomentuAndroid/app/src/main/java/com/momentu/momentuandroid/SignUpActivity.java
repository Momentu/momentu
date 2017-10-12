package com.momentu.momentuandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    EditText usernameInput, passwordInput, firstNameInput, lastNameInput, emailInput;

    //This string is to get the value of radio buttons
    String gender="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameInput = (EditText) findViewById(R.id.usernameInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        firstNameInput = (EditText) findViewById(R.id.firstNameInput);
        lastNameInput = (EditText) findViewById(R.id.lastNameInput);
        emailInput = (EditText) findViewById(R.id.emailInput);

    }

    //On click method for radio button and choosing gender type. It sets gender attribute with the user's choice
    public void onClickRadioButton(View v){
        Button radioButton = (Button) v;
        gender = radioButton.getText().toString();
    }

    //On click method for sign up button
    public void onClickSignUp(View v){

        if (checkFields()){
            Toast.makeText(this,"Registration is complete",Toast.LENGTH_LONG).show();
        }

    }

    //Check method to check the correctness of all data attribute entered by users
    public boolean checkFields(){

        if(usernameInput.getText().toString().isEmpty()){
            Toast.makeText(this,"Username can't be empty",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(passwordInput.getText().toString().isEmpty()){
            Toast.makeText(this,"Password can't be empty",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(firstNameInput.getText().toString().isEmpty()){
            Toast.makeText(this,"First name can't be empty",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(lastNameInput.getText().toString().isEmpty()){
            Toast.makeText(this,"Last name can't be empty",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(emailInput.getText().toString().isEmpty()){
            Toast.makeText(this,"Email name can't be empty",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(gender.toString().isEmpty()){
            Toast.makeText(this,"Please choose you gender",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
