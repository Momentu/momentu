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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RestClient {
    private static final String LOGIN_ENDPOINT = "http://www.momentu.xyz:8080/api/login";

    public static void login(final Map<String, String > params, final Activity currentActivity) {
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
                        if(currentActivity.getClass().equals(LoginActivity.class)) {
                            ((LoginActivity) currentActivity).showProgress(false);
                        }
                        Toast.makeText(currentActivity, "Server is not responding", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("response", response.body().string());
                if (response.code() == 200) {
                    String token = parseToken(response);

                    Intent intent = new Intent(currentActivity, HashTagSearchActivity.class);
                    intent.putExtra("token", token);
                    currentActivity.getApplicationContext().startActivity(intent);
                } else {
                    //I'm not able to show a message of failure.
                    new Handler(Looper.getMainLooper()).post(new Runnable() {

                        @Override
                        public void run() {
                            if(currentActivity.getClass().equals(LoginActivity.class)) {
                                ((LoginActivity) currentActivity).showProgress(false);
                            }
                            Toast.makeText(currentActivity, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

    }

    public static void register(final Map<String, String> params, final Activity currentActivity) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");


        JSONObject parameter = new JSONObject(params);
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, parameter.toString());
        Request request = new Request.Builder()

                .url("http://www.momentu.xyz:8080/api/register")
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
                Log.d("response", response.body().string());
                if(response.code()==200){
                    RestClient.login(params, currentActivity);
                }
                else{
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

    private static String parseToken(Response response)
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
