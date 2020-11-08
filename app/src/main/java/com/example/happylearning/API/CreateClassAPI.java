package com.example.happylearning.API;

import android.util.Log;

import com.example.happylearning.Data.Filedata;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import static com.example.happylearning.Login.LoginActivity.client;

public class CreateClassAPI {

    private String responseData;

    public CreateClassAPI(String class_password, String class_name,String account) {
        RequestBody requestBody = new FormBody.Builder()
                .add("class_password", class_password)
                .add("class_name",class_name)
                .add("class_teacher",account)
                .build();
        Request request = new Request.Builder()
                .url("http://42.194.219.209:8080//HappyLearning_Server//AddClass")
//                .url("http://192.168.43.89:8080//HappyLearning_Server//AddClass")
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