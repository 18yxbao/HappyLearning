package com.example.happylearning.API.HomeWork;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DownLoadFileAPI {
    private String responseData;
    private Response response = null;
    public DownLoadFileAPI(String file_path)
    {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("file_path", file_path)
                .build();
        Request request = new Request.Builder()
                .url("http://42.194.219.209:8080//HappyLearning_Server//DownLoadFile")
                .post(requestBody)
                .build();
        Log.d("JoinClassTest", "JoinClassTest");

        try {
            response = client.newCall(request).execute();
            Log.d("LoginTest", "onClick:" + responseData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Response getResponse()
    {
        return response;
    }
}
