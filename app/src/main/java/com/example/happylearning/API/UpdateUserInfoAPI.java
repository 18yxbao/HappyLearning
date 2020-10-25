package com.example.happylearning.API;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UpdateUserInfoAPI {
    /**
     * 教师发布公告：
     * "class_number" 课程号码
     * 	"notice_title" 公告标题
     * 	"notice_content" 公告内容
     * 	"notice_date" 发布公告时间 精确到秒
     */
    private String responseData="";

    public UpdateUserInfoAPI(String user_number, String user_name, String user_school, String user_schoolId, String user_major, String user_gender) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("user_number" , user_number)
                .add("user_name", user_name)
                .add("user_school", user_school)
                .add("user_schoolId", user_schoolId)
                .add("user_major", user_major)
                .add("user_gender", user_gender)
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.43.89:8080//HappyLearning_Server//UpdateUserInfo")
                .post(requestBody)
                .build();
        Log.d("Notice", "AddNotice");

        Response response = null;
        try {
            response = client.newCall(request).execute();
            responseData = response.body().string();
            Log.d("Notice", "onClick:" + responseData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponseData()
    {
        return responseData;
    }
}
