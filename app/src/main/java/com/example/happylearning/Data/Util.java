package com.example.happylearning.Data;


import android.content.Context;
import android.util.Log;

import com.example.happylearning.API.GetClassAPI;
import com.example.happylearning.API.GetUserInfoAndIconAPI;
import com.example.happylearning.API.HomeWork.GetHomeWorkListAPI;
import com.example.happylearning.Bean.Classes;
import com.example.happylearning.Bean.HomeWorkList;
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

    public static List<HomeWorkList> getHomeWorkLists(Context context, String class_num,String userNum, String account_type){
        List<HomeWorkList> homeWorkLists= new ArrayList<HomeWorkList>();
        GetHomeWorkListAPI api = new GetHomeWorkListAPI(class_num,userNum,account_type);
        String get_result = api.getResponseData();
        Log.d("getUserInfo", "getUserInfo: "+get_result);
        if(get_result==null||get_result.equals("fail")||get_result.equals("")){
            return homeWorkLists;
        }
        String[] result = get_result.split(",");
        int i;
        for( i=0;i<result.length;i=i+6){
            HomeWorkList homeWorkList=new HomeWorkList();
            homeWorkList.setType(result[i]);
            homeWorkList.setTitle(result[i+1]);
            homeWorkList.setFile_name(result[i+2]);
            homeWorkList.setPublic_time(result[i+3]);
            homeWorkList.setID(result[i+4]);
            homeWorkList.setIsSubmit(result[i+5]);
            homeWorkLists.add(homeWorkList);
        }
        //0:number 1:name 2:people_number

        return homeWorkLists;
    }
}
