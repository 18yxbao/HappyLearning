package com.example.happylearning.API;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DeleteNoticeAPI {
    /**
     * 教师删除公告：
     * "class_number" 课程号码
     * 	"notice_title" 公告标题 // 可传可不传
     * 	"notice_date" 发布公告时间 精确到秒 通过该属性判断唯一公告//通过该属性与公告标题两个属性判断唯一公告
     */
    private String responseData="";

    public DeleteNoticeAPI(String class_number, String notice_title, String notice_date) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("class_number", class_number)
                .add("notice_title", notice_title)
                .add("notice_date", notice_date)
                .build();
        Request request = new Request.Builder()
                .url("http://42.194.219.209:8080//HappyLearning_Server//DeleteNotice")
                .post(requestBody)
                .build();
        Log.d("Notice", "DeleteNotice");
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
