package com.example.happylearning.API;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import static com.example.happylearning.Login.LoginActivity.client;
public class GetWorkListAPI {
    private String responseData="";

    public GetWorkListAPI(String class_number,String user_number,String user_type){

        RequestBody requestBody = new FormBody.Builder()
                .add("class_number",class_number)
                .add("user_number", user_number)
                .add("user_type", user_type)
                .build();
        //Log.d("1234567899", "user_type: "+user_type);
        Request request = new Request.Builder()
//                .url("http://192.168.43.89:8080//HappyLearning_Server//GetPost")
                .url("http://42.194.219.209:8080//HappyLearning_Server//GetPost")
                .post(requestBody)
                .build();
        //Log.d("GetWorkListTest", "GetWorkListTest");
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


}
