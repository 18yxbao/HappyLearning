package com.example.happylearning.Bean;


public class UserInfo {
    private String number;  //手机号
    private String name;    //名字
    private String userIco; //头像链接
    private String schoolId;//学号
    private String major;   //专业
    private String school;  //学校
    private String gender;  //性别
    private String account_type; //0：学生；1：老师
//    private String age;     //出生年月


    public UserInfo() {
    }

    public UserInfo(String number, String name, String userIco, String schoolId,
                    String major, String school, String gender,String account_type) {
        this.number = number;
        this.name = name;
        this.userIco = userIco;
        this.schoolId = schoolId;
        this.major = major;
        this.school = school;
        this.gender = gender;
        this.account_type=account_type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserIco() {
        return userIco;
    }

    public void setUserIco(String userIco) {
        this.userIco = userIco;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", userIco='" + userIco + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", major='" + major + '\'' +
                ", school='" + school + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

}
