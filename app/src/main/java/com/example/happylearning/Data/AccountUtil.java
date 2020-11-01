package com.example.happylearning.Data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import java.io.File;

public class AccountUtil {
    private Boolean isLog;
    private String account;
    private String account_type;

    private String name;    //名字
    private String userIco; //头像
    private String schoolId;//学号
    private String major;   //专业
    private String school;  //学校
    private String gender;  //性别




    public static Boolean getLog(Context context){
        SharedPreferences sprfMain= context.getSharedPreferences("logindate",context.MODE_PRIVATE);
        Boolean isLog=sprfMain.getBoolean("islogin",false);
        sprfMain.edit();
        return isLog;
    }

    public static void setLog(Context context,Boolean log) {
        SharedPreferences.Editor editor = context.getSharedPreferences("logindate", context.MODE_PRIVATE).edit();
        editor.putBoolean("islogin", log);
        editor.apply();
    }

    public static String getAccount(Context context){
        SharedPreferences sprfMain= context.getSharedPreferences("logindate",context.MODE_PRIVATE);
        String account= sprfMain.getString("account","-1");
        sprfMain.edit();
        return account;
    }

    public static void setAccount(Context context,String account) {
        SharedPreferences.Editor editor = context.getSharedPreferences("logindate", context.MODE_PRIVATE).edit();
        editor.putString("account", account);
        editor.apply();
    }

    public static String getAccount_type(Context context){
        SharedPreferences sprfMain= context.getSharedPreferences("logindate",context.MODE_PRIVATE);
        String account_type=sprfMain.getString("account_type","-1");
        sprfMain.edit();
        return account_type;
    }

    public static void setAccount_type(Context context,String account_type) {
        SharedPreferences.Editor editor = context.getSharedPreferences("logindate", context.MODE_PRIVATE).edit();
        editor.putString("account_type", account_type);
        editor.apply();
    }


    public static void setUserInfo(Context context, String name, String userIco, String schoolId,
            String major, String school, String gender){

        String account=getAccount(context);
        SharedPreferences.Editor editor = context.getSharedPreferences(account, context.MODE_PRIVATE).edit();
        editor.putString("name", name);
        editor.putString("schoolId", schoolId);
        editor.putString("major", major);
        editor.putString("school", school);
        editor.putString("gender", gender);
        editor.apply();

        Bitmap bitmap=Filedata.stringtoBitmap(userIco);
        Filedata.savePicture(context,bitmap,"user_icon","user_icon.jpg");
    }

    public static String getName(Context context) {
        String account=getAccount(context);
        SharedPreferences sprfMain= context.getSharedPreferences(account,context.MODE_PRIVATE);
        String name=sprfMain.getString("name","");
        sprfMain.edit();
        return name;
    }

    public static Bitmap getUserIco(Context context) {
        String path=context.getExternalCacheDir()+ File.separator+"user_icon"+File.separator;
        String Picturename="user_icon.jpg";
        Bitmap bitmap=Filedata.loadPicture(context,path,Picturename);
        return bitmap;
    }

    public static String getSchoolId(Context context) {
        String account=getAccount(context);
        SharedPreferences sprfMain= context.getSharedPreferences(account,context.MODE_PRIVATE);
        String schoolId=sprfMain.getString("schoolId","");
        sprfMain.edit();
        return schoolId;
    }

    public static String getMajor(Context context) {
        String account=getAccount(context);
        SharedPreferences sprfMain= context.getSharedPreferences(account,context.MODE_PRIVATE);
        String major=sprfMain.getString("major","");
        sprfMain.edit();
        return major;
    }

    public static String getSchool(Context context) {
        String account=getAccount(context);
        SharedPreferences sprfMain= context.getSharedPreferences(account,context.MODE_PRIVATE);
        String school=sprfMain.getString("school","");
        sprfMain.edit();
        return school;
    }

    public static String getGender(Context context) {
        String account=getAccount(context);
        SharedPreferences sprfMain= context.getSharedPreferences(account,context.MODE_PRIVATE);
        String gender=sprfMain.getString("gender","");
        sprfMain.edit();
        return gender;
    }

    









}
