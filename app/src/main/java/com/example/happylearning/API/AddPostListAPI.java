package com.example.happylearning.API;

import android.util.Log;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 发送微博
 */
public class AddPostListAPI {

    private String responseData="";

    public AddPostListAPI(String class_number,String user_number, String user_name,String post_content,String content_type,
                          String image_number, String[] image_src, String post_date, String reply_id )
    {

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/octet-stream");//设置类型，类型为八位字节流

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)//添加表单数据
                .addFormDataPart("class_number", class_number)
                .addFormDataPart("user_number",user_number)
                .addFormDataPart("user_name",user_name)
                .addFormDataPart("post_content",post_content)
                .addFormDataPart("content_type",content_type)
                .addFormDataPart("image_number",image_number);

        for (int i=0;i<Integer.getInteger(image_number);i++){
            File file = new File(image_src[i]);//文件路径
            long size = file.length();//文件长度
            RequestBody requestBody = RequestBody.Companion.create(file,mediaType);//把文件与类型放入请求体
            builder.addFormDataPart("file"+i, file.getName(), requestBody);//文件名,请求体里的文件
        }
        builder.addFormDataPart("post_date",post_date)
                .addFormDataPart("reply_id",reply_id);
        MultipartBody multipartBody=builder.build();

        Request request = new Request.Builder()
                .header("Authorization", "Bearer d3e63518-1ba7-4342-b94c-63c8b9b9046b")//添加请求头的身份认证Token
                .url("http://42.194.219.209:8080//HappyLearning_Server//AddPost")
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
