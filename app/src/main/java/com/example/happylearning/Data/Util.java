package com.example.happylearning.Data;


import com.example.happylearning.API.GetClassAPI;
import com.example.happylearning.Bean.Classes;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static List<Classes> getClassList(String userNum,String account_type){
        List<Classes> classesList = new ArrayList<Classes>();
        GetClassAPI get = new GetClassAPI(userNum,account_type);
        String get_result = get.getResponseData();

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
}
