package com.example.happylearning.Data;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.happylearning.API.GetClassAPI;
import com.example.happylearning.Bean.Classes;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static List<Classes> getClassList(String studentNum,String account_type){
        List<Classes> classesList = new ArrayList<Classes>();
        GetClassAPI get = new GetClassAPI(studentNum,account_type);
        String get_result = get.getResponseData();

        if(get_result==null||get_result.equals("")) {
            Log.d("12345678", "NULL " + get_result);
            return null;
        }
        String[] result = get_result.split(",");
        Log.d("12345678", "get_result: "+get_result);
        //0:number 1:name 2:people_number
        int i;
        for( i=0;i<result.length;i=i+3){
            Classes classes=new Classes();
            classes.setClassNum(result[i]);
            Log.d("12345678", "getClassList: "+i);
            classes.setClassName(result[i+1]);
            classes.setClassPeopleNum(result[i+2]);
            classesList.add(classes);
            Log.d("12345678", "getClassList: "+i);
        }
        Log.d("12345678", "getClassList: "+classesList.toString());
        return classesList;
    }
}
