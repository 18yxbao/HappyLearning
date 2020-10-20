package com.example.happylearning.API;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class JoinClassAPI{
    private String responseData;

    public JoinClassAPI(String class_number, String class_password,String student_number)
    {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("class_number",class_number)
                .add("class_password", class_password)
                .add("student_number",student_number)
                .build();
        Request request = new Request.Builder()
                .url("http://42.194.219.209:8080//HappyLearning_Server//JoinClass")
                .post(requestBody)
                .build();
        Log.d("JoinClassTest", "JoinClassTest");
        Response response = null;
        try {
            response = client.newCall(request).execute();
            responseData = response.body().string();
            Log.d("LoginTest", "onClick:"+responseData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponseData()
    {
        return responseData;
    }
}
