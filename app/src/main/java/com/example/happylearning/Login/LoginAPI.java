package com.example.happylearning.Login;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginAPI extends Thread {
    //调用构造类，传入LoginActivity用户名编辑框获取到的用户名（str1）以及密码编辑框获取到的密码（str2）
    //若用户密码匹配，则responseData = ”success“字符串，若失败，则responseData = ”fail”


    private String responseData;
    private String username;
    private String password;

    public LoginAPI(String username,String password)
    {
        this.username = username;
        this.password = password;
    }

    @Override
    public void run() {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build();
        Request request = new Request.Builder()
                .url("http://42.194.219.209:8080//HappyLearning_Server//Login")
                .post(requestBody)
                .build();
        Log.d("LoginTest", "loginTest");
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
