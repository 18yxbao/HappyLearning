package com.example.happylearning.API.HomeWork;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import static com.example.happylearning.Login.LoginActivity.client;
public class DownLoadFileAPI {
    private String responseData;
    private Response response = null;
    public DownLoadFileAPI(String file_path)
    {
        RequestBody requestBody = new FormBody.Builder()
                .add("file_path", file_path)
                .build();
        Request request = new Request.Builder()
                .url("http://42.194.219.209:8080//HappyLearning_Server//DownLoadSpecialWork")
                .post(requestBody)
                .build();
        Log.d("JoinClassTest", "JoinClassTest");

        try {
            response = client.newCall(request).execute();
//            responseData = response.body().string();
//            Log.d("LoginTest", "onClick:" + responseData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Response getResponse()
    {
        return response;
    }
}
