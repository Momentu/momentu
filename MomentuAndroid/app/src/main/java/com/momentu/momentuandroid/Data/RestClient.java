package com.momentu.momentuandroid.Data;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.momentu.momentuandroid.HashTagSearchActivity;
import com.momentu.momentuandroid.LoginActivity;
import com.momentu.momentuandroid.Utility.RequestPackage;

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
    public static final String LOGIN_ENDPOINT = "http://www.momentu.xyz:8080/login";
    public static final String REGISTER_ENDPOINT = "http://www.momentu.xyz:8080/register";
    public static final String MEDIA_ENDPOINT = "http://www.momentu.xyz:8080/media";
    public static final String HASHTAGS_ENDPOINT = "http://www.momentu.xyz:8080/hashtags";


    private String token;
    public int status = 0;


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
    public void media(final Map<String, String> params, final String userToken, final Activity currentActivity)throws IOException {

        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        final JSONObject parameter = new JSONObject(params);
        new AsyncTask<String, Void, Response>()
        {
            public Request request;

            @Override
            protected void onPreExecute()
            {
            /* Called before task execution; from UI thread, so you can access your widgets */

                // Optionally do some stuff like showing progress bar

                RequestBody body = RequestBody.create(JSON, parameter.toString());

                request = new Request.Builder()
                .url(MEDIA_ENDPOINT)
                .post(body)
                .addHeader("content-type", "application/json; charset=utf-8")
                .addHeader("authorization", userToken)
                .build();
            };

            @Override
            protected Response doInBackground(String... url)
            {
            /* Called from background thread, so you're NOT allowed to interact with UI */

                // Perform heavy task to get YourObject by string
                // Stay clear & functional, just convert input to output and return it
                OkHttpClient client = new OkHttpClient();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return response;
            }

            @Override
            protected void onPostExecute(Response result)
            {
            /* Called once task is done; from UI thread, so you can access your widgets */

                // Process result as you like

                status = 0;
            }
        }.execute();
    }

    //this method is to send an http request to the :8080/hashtags endpoint
//    public void hashtags(final Map<String, String> params, final Activity currentActivity) {
//        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//
//        try{
//            Request request = new Request.Builder()
//                    .url(HASHTAGS_ENDPOINT)
////                .addPathSegment("search")
//                    .addHeader("city", params.get("city"))
//                    .addHeader("state", params.get("state"))
//                    .build();
//
//            OkHttpClient client = new OkHttpClient();
//
//            Log.d("request_method", request.method());
//
//            client.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    new Handler(Looper.getMainLooper()).post(new Runnable() {
//
//                        @Override
//                        public void run() {
//                            Toast.makeText(currentActivity, "Server is not responding", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    if (response.code() == 200) {
//                        Log.d("Hashtag_OnResponse", response.body().string());
//                    } else {
//                        new Handler(Looper.getMainLooper()).post(new Runnable() {
//
//                            @Override
//                            public void run() {
//                                Toast.makeText(currentActivity, "I went to the server but it looks like it is not happy", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                }
//            });
//        }
//        catch (java.lang.NullPointerException e){
//            Log.d("Hashtag failed", "Because location is null");
//        }
//
//    }
    public static String Hashtages(RequestPackage requestPackage)
            throws IOException {

        String address = requestPackage.getEndpoint();
        String function = requestPackage.getFunction();
        if(function != null)
            address += function;
        String encodedParams = requestPackage.getEncodedParams();

        address = String.format("%s?%s", address, encodedParams);

        Log.d("Adress",address);


        OkHttpClient client = new OkHttpClient();

        Request.Builder requestBuilder = new Request.Builder()
                .addHeader("authorization", requestPackage.getToken())
                .url(address);

        Request request = requestBuilder.build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Exception: response code " + response.code());
        }
    }

}