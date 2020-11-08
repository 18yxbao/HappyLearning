package com.example.happylearning.API.NoticeAPI;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import static com.example.happylearning.Login.LoginActivity.client;
public class ModifyNoticeAPI {
    /**
     * 教师修改公告：
     * 	"class_number" 课程号码
     * 	"notice_title" 修改后的公告标题
     * 	"notice_content" 修改后的公告内容
     * 	"notice_date_start" 原始发布公告时间 精确到秒
     * 	"notice_date_end" 修改公告时间 精确到秒
     * 返回：
     * 	"success" 发布成功
     * 	"fail" 发布失败
     */
    private String responseData="";

    public ModifyNoticeAPI(String class_number, String notice_title, String notice_date,
                           String notice_date_start, String notice_date_end) {
        RequestBody requestBody = new FormBody.Builder()
                .add("class_number", class_number)
                .add("notice_title", notice_title)
                .add("notice_date", notice_date)
                .add("notice_date_start", notice_date_start)
                .add("notice_date_end", notice_date_end)
                .build();
        Request request = new Request.Builder()
                .url("http://42.194.219.209:8080//HappyLearning_Server//ModifyNotice")
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
