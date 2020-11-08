package com.example.happylearning.API;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import static com.example.happylearning.Login.LoginActivity.client;
public class RegisterAPI {
    // 注意：调用该API前需先在注册活动处对两个密码框的密码是否一致进行判断和处理
    //调用构造类，传入RegisterActivity用户名编辑框获取到的用户名（str1）以及密码编辑框(任何一个）获取到的密码（str2）
    //若该用户名没有被注册过，即注册成功，则responseData = ”success“，若失败，则 = ”fail”
    private String responseData;

    public RegisterAPI(String username, String password,String account_type) {
        RequestBody requestBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .add("account_type", account_type)
                .build();
        Request request = new Request.Builder()
               // .url("http://192.168.43.89:8080//HappyLearning_Server//Register")
                .url("http://42.194.219.209:8080//HappyLearning_Server//Register")
                .post(requestBody)
                .build();
        Log.d("RegisterTest", "RegisterTest");
        Response response = null;
        try {
            response = client.newCall(request).execute();
            responseData = response.body().string();
            Log.d("RegisterTest", "onClick:" + responseData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponseData()
    {

        return responseData;
    }
}
