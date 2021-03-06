package com.momentu.momentuandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.momentu.momentuandroid.Data.RestClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    EditText usernameInput, passwordInput, firstNameInput, lastNameInput, emailInput;

    //This string is to get the value of radio buttons
    String gender = "";

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
    public void onClickRadioButton(View v) {
        Button radioButton = (Button) v;
        gender = radioButton.getText().toString();
    }

    //On click method for sign up button
    public void onClickSignUp(View v) throws IOException {

        if (checkFields()) {
//            User newUser = new User();
//            newUser.setUsername(usernameInput.getText().toString());
//            newUser.setPassword(passwordInput.getText().toString());
//            newUser.setFirstName(firstNameInput.getText().toString());
//            newUser.setLastName(lastNameInput.getText().toString());
//            newUser.setGender(gender.toString());
//            newUser.setEmail(emailInput.getText().toString());
//
//            Toast.makeText(SignUpActivity.this, newUser.toString(), Toast.LENGTH_LONG).show();

            Map<String, String> params = new HashMap<String, String>();
            params.put("username", usernameInput.getText().toString());
            params.put("password", passwordInput.getText().toString());
            params.put("firstName", firstNameInput.getText().toString());
            params.put("lastName", lastNameInput.getText().toString());
            params.put("gender", gender.toString());
            params.put("email",emailInput.getText().toString());

            new RestClient().register(params, SignUpActivity.this);

        }

    }

    //Check method to check the correctness of all data attribute entered by users
    public boolean checkFields() {
        View focusView=null;
        if (TextUtils.isEmpty(usernameInput.getText().toString())) {
            usernameInput.setError(getString(R.string.error_field_required));
            focusView = usernameInput;
            Toast.makeText(this, "Username can't be empty", Toast.LENGTH_LONG).show();
            return false;
        } else if (TextUtils.isEmpty(passwordInput.getText().toString())) {
            passwordInput.setError(getString(R.string.error_field_required));
            focusView = passwordInput;
            Toast.makeText(this, "Password can't be empty", Toast.LENGTH_LONG).show();
            return false;

        }else if (passwordInput.getText().length()<=8) {
            passwordInput.setError("Password length must be greater than 8 characters");
            focusView = passwordInput;
            return false;
        } else if (TextUtils.isEmpty(firstNameInput.getText().toString())) {
            firstNameInput.setError(getString(R.string.error_field_required));
            focusView = firstNameInput;
            Toast.makeText(this, "First name can't be empty", Toast.LENGTH_LONG).show();
            return false;
        } else if (TextUtils.isEmpty(lastNameInput.getText().toString())) {
            lastNameInput.setError(getString(R.string.error_field_required));
            focusView = lastNameInput;
            Toast.makeText(this, "Last name can't be empty", Toast.LENGTH_LONG).show();
            return false;
        } else if (TextUtils.isEmpty(emailInput.getText().toString())) {
            emailInput.setError(getString(R.string.error_field_required));
            focusView = emailInput;
            Toast.makeText(this, "Email name can't be empty", Toast.LENGTH_LONG).show();
            return false;
        } else if (!isEmailValid(emailInput.getText().toString())) {
            emailInput.setError("Invalid email format");
            focusView = emailInput;
            return false;
        } else if (gender.toString().isEmpty()) {
            Toast.makeText(this, "Please choose you gender", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    //This method is to check the format of an email that the user enters.
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}