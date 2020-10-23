package com.example.happylearning.API;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddNoticeAPI {
    /**
     * 教师发布公告：
     * "class_number" 课程号码
     * 	"notice_title" 公告标题
     * 	"notice_content" 公告内容
     * 	"notice_date" 发布公告时间 精确到秒
     */
    private String responseData="";

    public AddNoticeAPI(String class_number, String notice_title, String notice_content, String notice_date) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("class_number", class_number)
                .add("notice_title", notice_title)
                .add("notice_content", notice_content)
                .add("notice_date", notice_date)
                .build();
        Request request = new Request.Builder()
                .url("http://42.194.219.209:8080//HappyLearning_Server//AddNotice")
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
