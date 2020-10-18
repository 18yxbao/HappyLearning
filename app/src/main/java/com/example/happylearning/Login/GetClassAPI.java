package com.example.happylearning.Login;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GetClassAPI extends Thread{
    private String student_number;
    private String responseData;

    public GetClassAPI(String student_number)
    {
        this.student_number = student_number;
    }

    @Override
    public void run() {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("user_type","0")
                .add("student_number",student_number)
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.43.89:8080//HappyLearning_Server//GetClass")
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
