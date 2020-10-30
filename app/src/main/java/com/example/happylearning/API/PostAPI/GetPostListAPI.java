package com.example.happylearning.API.PostAPI;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.BlockingDeque;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GetPostListAPI {
    private String responseData="";

    public GetPostListAPI(String class_number,String user_number,String user_type,String reply_id){

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("class_number",class_number)
                .add("user_number", user_number)
                .add("user_type", user_type)
                .add("reply_id",reply_id)
                .build();
        Log.d("1234567899", "user_type: "+user_type);
        Request request = new Request.Builder()
//                .url("http://192.168.43.89:8080//HappyLearning_Server//GetPost")
                .url("http://42.194.219.209:8080//HappyLearning_Server//GetPost")
                .post(requestBody)
                .build();
        Log.d("JoinClassTest", "JoinClassTest");
        Response response = null;
        try {
            response = client.newCall(request).execute();
            responseData = response.body().string();
            Log.d("GetPostListAPI", "onClick:" + responseData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

}
