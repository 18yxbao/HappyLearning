package com.example.happylearning.Data;


import android.content.Context;
import android.util.Log;

import com.example.happylearning.API.GetClassAPI;
import com.example.happylearning.API.GetUserInfoAndIconAPI;
import com.example.happylearning.Bean.Classes;
import com.example.happylearning.Bean.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static List<Classes> getClassList(String userNum,String account_type){
        List<Classes> classesList = new ArrayList<Classes>();
        GetClassAPI get = new GetClassAPI(userNum,account_type);
        String get_result = get.getResponseData();
        Log.d("getClassList", "getClassList: "+get_result);
        if(get_result==null||get_result.equals("fail")||get_result.equals("")){
            return null;
        }
        String[] result = get_result.split(",");
        //0:number 1:name 2:people_number
        int i;
        for( i=0;i<result.length;i=i+3){
            Classes classes=new Classes();
            classes.setClassNum(result[i]);
            classes.setClassName(result[i+1]);
            classes.setClassPeopleNum(result[i+2]);
            classesList.add(classes);
        }
        return classesList;
    }

    public static UserInfo getUserInfo(Context context, String userNum, String account_type){

        GetUserInfoAndIconAPI api = new GetUserInfoAndIconAPI(userNum,account_type);

        String get_result = api.getResponseData();

        Log.d("getUserInfo", "getUserInfo: "+get_result);
        if(get_result==null||get_result.equals("fail")||get_result.equals("")){
            return null;
        }

        String[] result = get_result.split(",");
        //0:number 1:name 2:people_number
        UserInfo userInfo = new UserInfo(userNum, result[0], result[6], result[5],
                result[2], result[4], result[1],account_type);

        return userInfo;
    }
}
