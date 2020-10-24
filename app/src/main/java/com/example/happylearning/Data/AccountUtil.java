package com.example.happylearning.Data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class AccountUtil {
    private Boolean isLog;
    private String account;
    private String account_type;

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










}
