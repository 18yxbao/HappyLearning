package com.example.happylearning.API;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SeeNoticeListAPI {
    /**
     * 教师/学生查看公告列表：
     * "class_number" 课程号码
     * 返回：(公告1标题，公告1内容，公告1时间，公告2标题，…）
     * 	以逗号分隔返回一系列公告信息字符串，具体处理可借鉴util类
     */
    private String responseData="";

    public SeeNoticeListAPI(String class_number) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("class_number", class_number)
                .build();
        Request request = new Request.Builder()
                .url("http://42.194.219.209:8080//HappyLearning_Server//GetNotice")
                .post(requestBody)
                .build();
        Log.d("Notice", "SeeNoticeList");
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
