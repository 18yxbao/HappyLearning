package com.example.happylearning.API.HomeWork;

import android.util.Log;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import static com.example.happylearning.Login.LoginActivity.client;
public class SubmitWorkAPI {
    private String responseData;

    public SubmitWorkAPI(String file_name, String submitter_number, String submitter_name, String submit_time, String class_number, String work_id, String file_path) {

        File file = new File(file_path);//文件路径
        long size = file.length();//文件长度
        MediaType mediaType = MediaType.parse("application/octet-stream");//设置类型，类型为八位字节流
        RequestBody requestBody = RequestBody.Companion.create(file, mediaType);//把文件与类型放入请求体

        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)//添加表单数据
                .addFormDataPart("file_name", file_name)
                .addFormDataPart("submitter_number", submitter_number)
                .addFormDataPart("submitter_name", submitter_name)
                .addFormDataPart("submit_time", submit_time)
                .addFormDataPart("class_number", class_number)
                .addFormDataPart("work_id", work_id)
                .addFormDataPart("file", file.getName(), requestBody)//文件名,请求体里的文件
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Bearer d3e63518-1ba7-4342-b94c-63c8b9b9046b")//添加请求头的身份认证Token
                .url("http://42.194.219.209:8080//HappyLearning_Server//SubmitWork")
                .post(multipartBody)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            responseData = response.body().string();
            Log.d("LoginTest", "onClick:" + responseData);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getResponseData() {
        return responseData;
    }
}
