package com.example.happylearning.Data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Filedata {

    public static void save(String dataname, String responseText, Context context) {
        FileOutputStream outputStream = null;
        BufferedWriter bufferedWriter = null;
        try {
            outputStream = context.openFileOutput(dataname, Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write(responseText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null)
                    bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String load(String dataname, Context context) {

        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        StringBuilder content = new StringBuilder();
        try {
            fileInputStream = context.openFileInput(dataname);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    public static Bitmap stringtoBitmap(String string){
        //将字符串转换成Bitmap类型
        Bitmap bitmap=null;
        try {
            byte[]bitmapArray;
            bitmapArray= Base64.decode(string, Base64.DEFAULT);
            bitmap= BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public static void savePicture(Context context,Bitmap bitmap, String path, String file_name) {


        File bomb=new File(context.getApplicationContext().getExternalCacheDir(),path);
        if (!bomb.exists()){
            bomb.mkdir();
        }
        File file=new File(bomb,file_name);

        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Bitmap loadPicture(Context context,String path, String file_name) {
        File PHOTO_DIR = new File(context.getApplicationContext().getExternalCacheDir(),path);
        //读取本地图片
        Bitmap bitmap = null;
        try {
            File avaterFile = new File(PHOTO_DIR, file_name);
            if (avaterFile.exists()) {
                bitmap = BitmapFactory.decodeFile(PHOTO_DIR + file_name);
            }
        } catch (Exception e) {
        }
        return bitmap;
    }




}
