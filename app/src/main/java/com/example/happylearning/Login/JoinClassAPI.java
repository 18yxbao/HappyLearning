package com.example.happylearning.Login;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class JoinClassAPI extends Thread{
    private String class_number;
    private String class_password;
    private String student_number;
    private String responseData;

    public JoinClassAPI(String class_number, String class_password,String student_number)
    {
        this.class_number = class_number;
        this.class_password = class_password;
        this.student_number = student_number;
    }

    @Override
    public void run() {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("class_number",class_number)
                .add("class_password", class_password)
                .add("student_number",student_number)
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.43.89:8080//HappyLearning_Server//JoinClass")
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
