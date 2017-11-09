package com.momentu.momentuandroid.Data;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.momentu.momentuandroid.HashTagSearchActivity;
import com.momentu.momentuandroid.LoginActivity;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RestClient {
    private static final String LOGIN_ENDPOINT = "http://www.momentu.xyz:8080/login";
    private static final String REGISTER_ENDPOINT = "http://www.momentu.xyz:8080/register";
    private static final String HASHTAGS_ENDPOINT = "http://www.momentu.xyz:8080/hashtags";


    private String token;
    private int status = 0;


    public void login(final Map<String, String> params, final Activity currentActivity) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        JSONObject parameter = new JSONObject(params);
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, parameter.toString());
        final Request request = new Request.Builder()
                .url(LOGIN_ENDPOINT)
                .post(body)
                .addHeader("content-type", "application/json; charset=utf-8")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {

                    @Override
                    public void run() {
                        if (currentActivity.getClass().equals(LoginActivity.class)) {
                            ((LoginActivity) currentActivity).showProgress(false);
                        }
                        Toast.makeText(currentActivity, "Server is not responding", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                token = response.body().string();
                token = token.split(",")[0].split(":")[1];
                token = "Bearier " + token.substring(1, token.length() - 1);
                if (response.code() == 200) {
                    Intent intent = new Intent(currentActivity, HashTagSearchActivity.class);
                    intent.putExtra("token", token);
                    currentActivity.getApplicationContext().startActivity(intent);
                } else {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {

                        @Override
                        public void run() {
                            if (currentActivity.getClass().equals(LoginActivity.class)) {
                                ((LoginActivity) currentActivity).showProgress(false);
                            }
                            Toast.makeText(currentActivity, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    public void register(final Map<String, String> params, final Activity currentActivity) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");


        JSONObject parameter = new JSONObject(params);
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, parameter.toString());
        Request request = new Request.Builder()

                .url(REGISTER_ENDPOINT)
                .post(body)
                .addHeader("content-type", "application/json; charset=utf-8")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(currentActivity, "Server is not responding", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("Signup_response", response.body().string());
                if (response.code() == 200) {
                    login(params, currentActivity);
                } else {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(currentActivity, "Email or Username has already being used", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    //this method is to send an http request to the :8080/media endpoint
    public int media(final Map<String, String> params, final String userToken, final Activity currentActivity) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");


        JSONObject parameter = new JSONObject(params);
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, parameter.toString());
        Request request = new Request.Builder()

                .url(MEDIA_ENDPOINT)
                .post(body)
                .addHeader("content-type", "application/json; charset=utf-8")
                .addHeader("authorization", userToken)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("media_OnFailure", e.getMessage());

                new Handler(Looper.getMainLooper()).post(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(currentActivity, "Server is not responding", Toast.LENGTH_SHORT).show();
                        status = -1;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("media_OnResponse", response.body().string());
                if (response.code() == 200) {
                } else {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(currentActivity, "I went to the server but it looks like it is not happy", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return status;
    }

//    //this method is to send an http request to the :8080/hashtags endpoint
//    public void hashtags(final Map<String, String> params, final Activity currentActivity) {
//        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//
//
//        JSONObject parameter = new JSONObject(params);
//        OkHttpClient client = new OkHttpClient();
//
//        RequestBody body = RequestBody.create(JSON, parameter.toString());
//        Request request = new Request.Builder()
//
//                .url(HASHTAGS_ENDPOINT)
//                .get()
//                .addHeader("content-type", "application/json; charset=utf-8")
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                new Handler(Looper.getMainLooper()).post(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        Toast.makeText(currentActivity, "Server is not responding", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.d("Hashtag_OnResponse", response.body().string());
//                if (response.code() == 200) {
//
//                } else {
//                    new Handler(Looper.getMainLooper()).post(new Runnable() {
//
//                        @Override
//                        public void run() {
//                            Toast.makeText(currentActivity, "I went to the server but it looks like it is not happy", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//            }
//        });
//    }
}