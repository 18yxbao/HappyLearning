package com.example.happylearning.API;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GetUserInfoAndIconAPI {
    private String responseData;

    public GetUserInfoAndIconAPI(String user_number,String account_type)
    {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("user_number", user_number)
                .add("user_type", account_type)
                .build();
        Request request = new Request.Builder()
                .url("http://42.194.219.209:8080//HappyLearning_Server//GetUserInfoAndIcon")
                .post(requestBody)
                .build();
        Log.d("JoinClassTest", "JoinClassTest");
        Response response = null;
        try {
            response = client.newCall(request).execute();
            responseData = response.body().string();
            Log.d("LoginTest", "onClick:" + responseData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getResponseData()
    {
        return responseData;
    }
}
