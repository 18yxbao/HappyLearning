package com.example.happylearning.API.HomeWork;

import android.util.Log;

import java.io.File;
import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class AddHomeWorkAPI {

    private String responseData;

    public AddHomeWorkAPI(String work_title, String work_content, String limit_time, String public_time, String class_number, String work_type, String file_path) {
//        OkHttpClient client = new OkHttpClient();
//        RequestBody requestBody = new FormBody.Builder()
//                .add("work_title", work_title)
//                .add("work_content",work_content)
//                .add("limit_time",limit_time)
//                .add("post_time",post_time)
//                .add("class_number",class_number)
//                .add("work_type",work_type)
//                .add("file ",file_path)
//                .build();
//        Request request = new Request.Builder()
//                .url("http://42.194.219.209:8080//HappyLearning_Server//AddHomeWork")
//                .post(requestBody)
//                .build();
//        Log.d("CreateClassAPI", "JoinClassTest");
//        Response response = null;
//        try {
//            response = client.newCall(request).execute();
//            responseData = response.body().string();
//            Log.d("CreateClassAPI", "onClick:" + responseData);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
            MultipartBody multipartBody;
            OkHttpClient client = new OkHttpClient();

            if(!file_path.equals("0")) {
                File  file = new File(file_path);//文件路径
                String[] result = file_path.split("/");
                long size = file.length();//文件长度
                MediaType mediaType = MediaType.parse("application/octet-stream");//设置类型，类型为八位字节流
                RequestBody requestBody = RequestBody.Companion.create(file, mediaType);//把文件与类型放入请求体
                multipartBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)//添加表单数据
                        .addFormDataPart("work_title", work_title)
                        .addFormDataPart("work_content", work_content)
                        .addFormDataPart("limit_time", limit_time)
                        .addFormDataPart("class_number", class_number)
                        .addFormDataPart("work_type", work_type)
                        .addFormDataPart("file_name",result[result.length-1] )
                        .addFormDataPart("public_time", public_time)
                        .addFormDataPart("file", file.getName(), requestBody)//文件名,请求体里的文件
                        .build();
            }
            else{
                multipartBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)//添加表单数据
                        .addFormDataPart("work_title", work_title)
                        .addFormDataPart("work_content", work_content)
                        .addFormDataPart("limit_time", limit_time)
                        .addFormDataPart("class_number", class_number)
                        .addFormDataPart("work_type", work_type)
                        .addFormDataPart("file_name", "0")
                        .addFormDataPart("public_time", public_time)
                        .addFormDataPart("file", "0")//文件名,请求体里的文件
                        .build();
            }




            Request request = new Request.Builder()
                    .header("Authorization", "Bearer d3e63518-1ba7-4342-b94c-63c8b9b9046b")//添加请求头的身份认证Token
                    .url("http://42.194.219.209:8080//HappyLearning_Server//AddHomeWork")
                    //.url("http://192.168.43.89:8080//HappyLearning_Server//AddHomeWork")
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