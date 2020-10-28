package com.example.happylearning.API.PostAPI;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DeletePostListAPI {
    private String responseData="";

    public DeletePostListAPI(String class_number, String post_id){

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("class_number",class_number)
                .add("post_id", post_id)
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.43.89:8080//HappyLearning_Server//DeletePost")
                .post(requestBody)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            responseData = response.body().string();
            Log.d("DeletePostAPI", "onClick:" + responseData);
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
