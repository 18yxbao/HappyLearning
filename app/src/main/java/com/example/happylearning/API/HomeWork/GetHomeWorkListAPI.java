package com.example.happylearning.API.HomeWork;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import static com.example.happylearning.Login.LoginActivity.client;

public class GetHomeWorkListAPI {

    private String responseData;

    public GetHomeWorkListAPI(String class_number, String user_number, String user_type) {
        RequestBody requestBody = new FormBody.Builder()
                .add("class_number", class_number)
                .add("user_number",user_number)
                .add("user_type",user_type)
                .build();
        Request request = new Request.Builder()
                .url("http://42.194.219.209:8080//HappyLearning_Server//GetHomeWorkList")
              //  .url("http://192.168.43.89:8080//HappyLearning_Server//GetHomeWorkList")

                .post(requestBody)
                .build();
        Log.d("CreateClassAPI", "JoinClassTest");
        Response response = null;
        try {
            response = client.newCall(request).execute();
            responseData = response.body().string();
            Log.d("CreateClassAPI", "onClick:" + responseData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponseData() {
        return responseData;
    }
}