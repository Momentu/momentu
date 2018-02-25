package com.momentu.momentuandroid.Data;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.momentu.momentuandroid.FeedActivity;
import com.momentu.momentuandroid.HashTagSearchActivity;
import com.momentu.momentuandroid.LoginActivity;
import com.momentu.momentuandroid.Utility.RequestPackage;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RestClient {
    private String token;
    public int status = 0;


    public void login(final Map<String, String> params, final Activity currentActivity) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        JSONObject parameter = new JSONObject(params);
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, parameter.toString());
        final Request request = new Request.Builder()
                .url(EndPoints.LOGIN_ENDPOINT)
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
                token = "Bearer " + token.substring(1, token.length() - 1);
                if (response.code() == 200) {
                    Intent intent = new Intent(currentActivity, HashTagSearchActivity.class);
                    intent.putExtra("token", token);
                    intent.putExtra("username", params.get("username"));
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

                .url(EndPoints.REGISTER_ENDPOINT)
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
    public void media(final Map<String, String> params, final String userToken, final Activity currentActivity) throws IOException {

        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        final JSONObject parameter = new JSONObject(params);
        new AsyncTask<String, Void, Response>() {
            public Request request;

            @Override
            protected void onPreExecute() {
            /* Called before task execution; from UI thread, so you can access your widgets */

                // Optionally do some stuff like showing progress bar

                RequestBody body = RequestBody.create(JSON, parameter.toString());

                request = new Request.Builder()
                        .url(EndPoints.MEDIA_ENDPOINT)
                        .post(body)
                        .addHeader("content-type", "application/json; charset=utf-8")
                        .addHeader("authorization", userToken)
                        .build();
            }

            ;

            @Override
            protected Response doInBackground(String... url) {
            /* Called from background thread, so you're NOT allowed to interact with UI */

                // Perform heavy task to get YourObject by string
                // Stay clear & functional, just convert input to output and return it
                OkHttpClient client = new OkHttpClient();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    Log.d("Response_M",response.code() + "");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return response;
            }

            @Override
            protected void onPostExecute(Response result) {
            /* Called once task is done; from UI thread, so you can access your widgets */

                // Process result as you like

                status = 0;
            }
        }.execute();
    }

    public static String get(RequestPackage requestPackage)
            throws IOException {

        String address = requestPackage.getEndpoint();
        String function = requestPackage.getFunction();
        if (function != null)
            address += function;
        String encodedParams = requestPackage.getEncodedParams();

        address = String.format("%s?%s", address, encodedParams);

        Log.d("Adress", address);

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

    public void media_upload(final byte[] media, final String mediaType,final Map<String, String> params, final String userToken, final Activity currentActivity) throws IOException {
        final MediaType MEDIA_TYPE_JPEG = MediaType.parse(mediaType=="image"? "image/JPEG":"video/mp4");//"image/JPEG"
        OkHttpClient client = new OkHttpClient();
        Log.d("Response_M_U_Not_yet", "just got in");

        new AsyncTask<String, Void, Response>() {
            public Request request;
//            String path_external = Environment.getExternalStorageDirectory() + File.separator + "temporary_file.jpg";
//            File file;
            @Override
            protected void onPreExecute() {
            /* Called before task execution; from UI thread, so you can access your widgets */
                // Optionally do some stuff like showing progress bar
                      }
            @Override
            protected Response doInBackground(String... url) {
            /* Called from background thread, so you're NOT allowed to interact with UI */
            // Perform heavy task to get YourObject by string
                // Stay clear & functional, just convert input to output and return it
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("file","",RequestBody.create(MEDIA_TYPE_JPEG, media))
                        .addFormDataPart("mediaType",mediaType)
                        .addFormDataPart("city",params.get("city"))
                        .addFormDataPart("hashtagLabel",params.get("hashtagLabel"))
                        .addFormDataPart("state",params.get("state"))
                        .build();

                request = new Request.Builder().url(EndPoints.MEDIA_UPLOAD_ENDPOINT)
                        .addHeader("content-type", "application/json; charset=utf-8")
                        .addHeader("authorization", userToken)
                        .post(requestBody).build();
                ///*****************************************************************\\\\\
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    Log.d("Response_M_U",response.code() + "");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("Response_M_U"," it failed");

                }
                catch (Exception e){
                    e.printStackTrace();
                }
                return response;
            }

            @Override
            protected void onPostExecute(Response result) {
            /* Called once task is done; from UI thread, so you can access your widgets */

                // Process result as you like

                status = 0;
            }
        }.execute();
    }

    public static String retrieve_media(final RequestPackage requestPackage)
            throws IOException {

        Response response;

        String encodedParams = requestPackage.getEncodedParams();
        String address = String.format("%s?%s", EndPoints.MEDIA_RETRIVE, encodedParams);

        Log.d("Adress_retreive", address);

        OkHttpClient client = new OkHttpClient();

        Request.Builder requestBuilder = new Request.Builder()
                .addHeader("authorization", requestPackage.getToken())
                .url(address);

        Request request = requestBuilder.build();
        response = null;
        response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Exception: response code " + response.code());
        }

    }
    public void MediaLike(final long mediaId, final String newToken) throws IOException {

        Log.d("MediaLike","just got in like");

        new AsyncTask<String, Void, Response>() {
            public Request request;
            //            String path_external = Environment.getExternalStorageDirectory() + File.separator + "temporary_file.jpg";
//            File file;
            @Override
            protected void onPreExecute() {
            /* Called before task execution; from UI thread, so you can access your widgets */
                // Optionally do some stuff like showing progress bar
            }
            @Override
            protected Response doInBackground(String... url) {
            /* Called from background thread, so you're NOT allowed to interact with UI */
                // Perform heavy task to get YourObject by string
                // Stay clear & functional, just convert input to output and return it
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("", "")
                        .build();

                request = new Request.Builder().url(EndPoints.MEIDALIKE+"?mediaMetaId="+mediaId)
                        .addHeader("content-type", "application/json; charset=utf-8")
                        .addHeader("authorization", newToken)
                        .post(requestBody).build();
                ///*****************************************************************\\\\\
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    Log.d("Response_M_U",response.code() + "");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("Response_M_U"," it failed");

                }
                catch (Exception e){
                    e.printStackTrace();
                }
                return response;
            }

            @Override
            protected void onPostExecute(Response result) {
            /* Called once task is done; from UI thread, so you can access your widgets */

                // Process result as you like

                status = 0;
            }
        }.execute();
    }
    public void MediaUnlike(final long mediaId, final String newToken) throws IOException {

        Log.d("MediaLike","just got in unlike");

        new AsyncTask<String, Void, Response>() {
            public Request request;
            //            String path_external = Environment.getExternalStorageDirectory() + File.separator + "temporary_file.jpg";
//            File file;
            @Override
            protected void onPreExecute() {
            /* Called before task execution; from UI thread, so you can access your widgets */
                // Optionally do some stuff like showing progress bar
            }
            @Override
            protected Response doInBackground(String... url) {
            /* Called from background thread, so you're NOT allowed to interact with UI */
                // Perform heavy task to get YourObject by string
                // Stay clear & functional, just convert input to output and return it
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("", "")
                        .build();

                request = new Request.Builder().url(EndPoints.MEIDAUNLIKE+"?mediaMetaId="+mediaId)
                        .addHeader("content-type", "application/json; charset=utf-8")
                        .addHeader("authorization", newToken)
                        .post(requestBody).build();
                ///*****************************************************************\\\\\
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    Log.d("Response_M_U",response.code() + "");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("Response_M_U"," it failed");

                }
                catch (Exception e){
                    e.printStackTrace();
                }
                return response;
            }

            @Override
            protected void onPostExecute(Response result) {
            /* Called once task is done; from UI thread, so you can access your widgets */

                // Process result as you like

                status = 0;
            }
        }.execute();
    }

    public static String retrieve_comments(final long mediaId, final String token) throws IOException {
        Response response;

        String address = String.format("%s?%s", EndPoints.RETRIEVECOMMENTS, "mediaMetaId=" + mediaId);

        Log.d("Adress_retreive", address);

        OkHttpClient client = new OkHttpClient();

        Request.Builder requestBuilder = new Request.Builder()
                .addHeader("authorization", token)
                .url(address);

        Request request = requestBuilder.build();
        response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response.isSuccessful()) {
            try {
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new IOException("Exception: response code " + response.code());
        }
        return null;
    }
    public int post_comment(final Map<String, String> params) throws IOException, ExecutionException, InterruptedException {

        Log.d("post_comment","got in " + params.toString());
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        final JSONObject parameter = new JSONObject(params);
        Response response = new AsyncTask<String, Void, Response>() {
            public Request request;

            @Override
            protected void onPreExecute() {
            /* Called before task execution; from UI thread, so you can access your widgets */

                // Optionally do some stuff like showing progress bar

                RequestBody body = RequestBody.create(JSON, "");

                request = new Request.Builder()
                        .url(EndPoints.POSTCOMMENT+"?mediaMetaId="+ params.get("mediaMetaId")+"&comment="+ params.get("comment"))
                        .post(body)
                        .addHeader("content-type", "application/json; charset=utf-8")
                        .addHeader("authorization", FeedActivity.token)
                        .build();
            }

            ;

            @Override
            protected Response doInBackground(String... url) {
            /* Called from background thread, so you're NOT allowed to interact with UI */

                // Perform heavy task to get YourObject by string
                // Stay clear & functional, just convert input to output and return it
                OkHttpClient client = new OkHttpClient();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    Log.d("Response_Post_Comm",response.code() + "");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return response;
            }


            @Override
            protected void onPostExecute(Response result) {
            /* Called once task is done; from UI thread, so you can access your widgets */

                // Process result as you like

                status = 0;
            }
        }.execute().get();
        return response.code();
    }
}