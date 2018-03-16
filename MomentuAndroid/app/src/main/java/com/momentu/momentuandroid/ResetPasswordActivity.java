package com.momentu.momentuandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.momentu.momentuandroid.Data.RestClient;

import java.io.IOException;
import java.util.List;

public class ResetPasswordActivity extends AppCompatActivity {

    TextView newPassword;
    TextView confirmNewPassword;

    String token = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_step_reset_password);
        newPassword = (TextView)findViewById(R.id.new_password);
        confirmNewPassword = (TextView)findViewById(R.id.confirm_new_password);

        Intent intent = getIntent();
        if (intent != null && intent.getData() != null ){


//            List<String> params = intent.getData().getPathSegments();
            token = intent.getData().getQueryParameter("token");
//            String somestuff = params.get(1);
//            ((TextView) findViewById(R.id.textView)).setText(token);
        }
    }
    //On Click method to go to term and policy activity
    public void onClickResetPassword(View v){
        String password = newPassword.getText().toString();
        String ConfirmePassword = confirmNewPassword.getText().toString();

        if (password.equals(ConfirmePassword)){
            if(token != null){
                try {
                    new RestClient().resetPassword(ConfirmePassword, token);
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                Toast.makeText(ResetPasswordActivity.this,
                        "Something is not right. Please re-click on the link sent to your email.",
                        Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(ResetPasswordActivity.this, "The two passwords are not match. Please double check them.",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
