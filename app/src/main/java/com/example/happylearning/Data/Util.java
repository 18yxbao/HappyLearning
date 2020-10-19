package com.example.happylearning.Data;

import android.util.Log;

import com.example.happylearning.API.GetClassAPI;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static List<Classes> getClassList(String studentNum){
        List<Classes> classesList = new ArrayList<Classes>();
        GetClassAPI get = new GetClassAPI(studentNum);

        String get_result = get.getResponseData();
        if(get_result==null)
            return null;
        String[] result = get_result.split(",");
        Log.d("12345678", "getClassList: "+result[0]);
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
