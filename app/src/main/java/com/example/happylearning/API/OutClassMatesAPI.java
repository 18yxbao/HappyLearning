package com.example.happylearning.API;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import static com.example.happylearning.Login.LoginActivity.client;

public class OutClassMatesAPI {

    private String responseData;

    public OutClassMatesAPI(String class_number, String out_number, List<String> deleteMember) {
        FormBody.Builder temp = new FormBody.Builder();
        temp.add("class_number", class_number).add("out_number",out_number);
        for(int i=0;i < Integer.parseInt(out_number); i++){
            temp .add("stu_number"+(i+1),deleteMember.get(i));
        }

        RequestBody requestBody = temp.build();
        Request request = new Request.Builder()
                .url("http://42.194.219.209:8080//HappyLearning_Server//OutClassMates")
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