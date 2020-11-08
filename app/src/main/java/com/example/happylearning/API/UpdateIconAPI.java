package com.example.happylearning.API;

import android.util.Log;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import static com.example.happylearning.Login.LoginActivity.client;
public class UpdateIconAPI {

    private String responseData="";

    public UpdateIconAPI(String image_src,String user_number,String user_type)
    {
        File file = new File(image_src);//文件路径
        long size = file.length();//文件长度
        MediaType mediaType = MediaType.parse("application/octet-stream");//设置类型，类型为八位字节流
        RequestBody requestBody = RequestBody.Companion.create(file,mediaType);//把文件与类型放入请求体

        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)//添加表单数据
                .addFormDataPart("user", user_number)
                .addFormDataPart("user_type", user_type)
                .addFormDataPart("file", file.getName(), requestBody)//文件名,请求体里的文件
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Bearer d3e63518-1ba7-4342-b94c-63c8b9b9046b")//添加请求头的身份认证Token
                .url("http://42.194.219.209:8080//HappyLearning_Server//UpdateIcon")
//                .url("http://192.168.43.89:8080//HappyLearning_Server//UpdateIcon")
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

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }
}
