package com.example.happylearning.API;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GetClassTeacherAPI {
    /**
     * 教师发布公告：
     * "class_number" 课程号码
     * 	"notice_title" 公告标题
     * 	"notice_content" 公告内容
     * 	"notice_date" 发布公告时间 精确到秒
     */
    private String responseData="";

    public GetClassTeacherAPI(String class_number) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("class_number", class_number)
                .build();
        Request request = new Request.Builder()
                .url("http://42.194.219.209:8080//HappyLearning_Server//GetClassTeacher")
//                .url("http://192.168.43.89:8080//HappyLearning_Server//GetClassTeacher")
                .post(requestBody)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            responseData = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("123456", "GetClassTeacherAPI: "+responseData);
    }

    public String getResponseData()
    {
        return responseData;
    }
}
