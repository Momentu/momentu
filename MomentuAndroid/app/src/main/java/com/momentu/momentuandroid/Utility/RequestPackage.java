package com.momentu.momentuandroid.Utility;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ibm_a on 11/10/2017.
 */

public class RequestPackage implements Parcelable {

//    private String city;
//    private String state;
    private String endpoint;
    private String method = "GET";
    private String token;
    private String function;
    private Map<String, String> params = new HashMap<>();


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public void setParam(String key, String value) {
        params.put(key, value);
    }

    public String getEncodedParams() {
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()) {
            String value = null;
            try {
                value = URLEncoder.encode(params.get(key), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(key).append("=").append(value);
        }
        return sb.toString();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.endpoint);
        dest.writeString(this.token);
        dest.writeString(this.function);
        dest.writeInt(this.params.size());
        for (Map.Entry<String, String> entry : this.params.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
    }

    public RequestPackage() {
    }

    protected RequestPackage(Parcel in) {
        this.endpoint = in.readString();
        this.token = in.readString();
        this.function = in.readString();
        int paramsSize = in.readInt();
        this.params = new HashMap<String, String>(paramsSize);
        for (int i = 0; i < paramsSize; i++) {
            String key = in.readString();
            String value = in.readString();
            this.params.put(key, value);
        }
    }

    public static final Parcelable.Creator<RequestPackage> CREATOR = new Parcelable.Creator<RequestPackage>() {
        @Override
        public RequestPackage createFromParcel(Parcel source) {
            return new RequestPackage(source);
        }

        @Override
        public RequestPackage[] newArray(int size) {
            return new RequestPackage[size];
        }
    };
}