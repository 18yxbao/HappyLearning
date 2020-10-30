package com.example.happylearning.API.PostAPI;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 点赞/取消点赞
 * class_number
 * user_number
 * post_id
 * if_like（操作为点赞，传0，取消点赞，传1）
 */
public class StarPostListAPI {
    private String responseData="";

    public StarPostListAPI(String class_number, String user_number, String user_type, String post_id,String if_like){

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("class_number",class_number)
                .add("user_number",user_number)
                .add("user_type",user_type)
                .add("post_id", post_id)
                .add("if_like", if_like)
                .build();
        Request request = new Request.Builder()
                .url("http://42.194.219.209:8080//HappyLearning_Server//PostLike")
//                .url("http://192.168.43.89:8080//HappyLearning_Server//PostLike")
                .post(requestBody)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            responseData = response.body().string();
            Log.d("StarPostAPI", "onClick:" + responseData);
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
