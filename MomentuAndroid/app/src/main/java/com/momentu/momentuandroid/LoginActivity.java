package com.momentu.momentuandroid;

/**
 * Created by Jane on 10/12/2017.
 */

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A login screen that offers login via username/password.
 */
public class LoginActivity extends Activity {

//    private User myUser;

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
//    private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText mUsernameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mUsernameView = (EditText) findViewById(R.id.username);

        // Get user password input from the password field.
        mPasswordView = (EditText) findViewById(R.id.password);
        // Get user password input from the password field.
      //  mPasswordView = (EditText) findViewById(R.id.password);

        Button mUsernameLogInButton = (Button) findViewById(R.id.login_button);
        mUsernameLogInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null :
                        getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid username, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
//        if (mAuthTask != null) {
//            return;
//        }

        // Reset errors.
        mUsernameView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check empty password.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check empty username.
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            Map<String, String> params = new HashMap<String, String>();
            params.put("username", mUsernameView.getText().toString());
            params.put("password", mPasswordView.getText().toString());

            httpHandler(params);
        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
//    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
//
//        private final String mUsername;
//        private final String mPassword;
//        private final Context mContext;
//
//        UserLoginTask(String username, String password, Context context) {
//            mUsername = username;
//            mPassword = password;
//            mContext = context;
//        }
//
//        @Override
//        protected Boolean doInBackground(Void... params) {
////            DBTools dbTools = null;
//
//                Map<String, String> params1 = new HashMap<String, String>();
//                params1.put("username", mUsername);
//                params1.put("password", mPassword);
//            Response response = null;
//            try {
//                response = new OkHttpHandler().httpHanler(1, params1);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//            // Check if user exit, if yes, check the password, if not, create new account.
////            try {
////                dbTools = new DBTools(mContext);
////                myUser = dbTools.getUser(mUsername);
////
////                if (myUser.userId > 0) {
////                    // Account exists, check password.
////                    if (myUser.password.equals(mPassword))
////                        return true;
////                    else
////                        return false;
////                } else {
////                    myUser.password = mPassword;
////                    return true;
////                }
////            } finally {
////               if (dbTools != null)
////                   dbTools.close();
////            }
//            return true;
//        }
//
//        @Override
//        protected void onPostExecute(final Boolean success) {
//            mAuthTask = null;
//            showProgress(false);
//
//            if (success) {
////                if (myUser.userId > 0) {
//                  // TODO: If user exists, then go to the search page.
//                      Intent search = new Intent(LoginActivity.this, SearchActivity.class);
////                    Log.d("Passing User ",  myUser.username);
////                    myIntent.putExtra("newUser", FALSE);
////                    myIntent.putExtra("userName", myUser.username);
//                    startActivity(search);
////                } else {
//                  // TODO: If user does not exist, then go to the sign in page.
////                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
////                        @Override
////                        public void onClick(DialogInterface dialog, int which) {
////                            switch (which) {
////                                case DialogInterface.BUTTON_POSITIVE:
////                                    DBTools dbTools = null;
////                                    // Switch to the sign up page to create a new account.
////                                    try {
////                                        dbTools = new DBTools(mContext);
////                                        myUser = dbTools.insertUser(myUser);
////                                        finish();
////                                        Intent myIntent = new Intent(LoginActivity.this, SignUpActivity.class);
////                                        Log.d("Passing User ",  myUser.username);
////                                        LoginActivity.this.startActivity(myIntent);
////                                    } finally {
////                                        if (dbTools != null)
////                                            dbTools.close();
////                                    }
////                                    break;
////
////                                case DialogInterface.BUTTON_NEGATIVE:
////                                    mUsernameView.setError(getString(R.string.username_not_exist));
//////                                    mPasswordView.setError(getString(R.string.error_incorrect_password));
////                                    mUsernameView.requestFocus();
////                                    break;
////                            }
////                        }
////                    };
////
////                    AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
////                    builder.setMessage(R.string.confirm_registry).setPositiveButton(R.string.yes, dialogClickListener)
////                            .setNegativeButton(R.string.no, dialogClickListener).show();
////                }
//            } else {
//                mPasswordView.setError(getString(R.string.error_incorrect_password));
//                mPasswordView.requestFocus();
//            }
////        }
//
////        @Override
////        protected void onCancelled() {
////            mAuthTask = null;
////            showProgress(false);
////        }
//        }
//    }

    private void httpHandler(Map<String, String > params) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");


        JSONObject parameter = new JSONObject(params);
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, parameter.toString());
        final Request request = new Request.Builder()
                .url("http://192.168.157.1:8080/api/login")
                .post(body)
                .addHeader("content-type", "application/json; charset=utf-8")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {

                    @Override
                    public void run() {
                        showProgress(false);
                        Toast.makeText(LoginActivity.this, "Server is not responding", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("response", response.body().string());
                if (response.code() == 200) {
                   String token = parse(response);

                    Intent intent = new Intent(LoginActivity.this, SearchActivity.class);
                    intent.putExtra("token", token);
                    startActivity(intent);
                } else {
                    //I'm not able to show a message of failure.
                    new Handler(Looper.getMainLooper()).post(new Runnable() {

                        @Override
                        public void run() {
                            showProgress(false);
                            Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });}
        private String parse(Response response)
    {
        String result = null;
        InputStream inputStream = null;
        String token = null;
        try {
            inputStream = response.body().byteStream();

            // json is UTF-8 by default
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            result = sb.toString();

            JSONObject jObject = new JSONObject(result);
            token = jObject.getString("token");
            Log.d("Logintest", "Token After parsing process" + token);


        } catch (Exception e) {
            // Oops
        } finally {
            try {
                if (inputStream != null) inputStream.close();
            } catch (Exception squish) {
            }
        }
        return token;
    }

}
