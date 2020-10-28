package com.example.happylearning.Bean;

import android.graphics.Bitmap;

public class PostBean {
    private Bitmap icon;
    private String userid;
    private String username;
    private String time;
    private String content;
    private String pictureNum;  //图片数量
    private Bitmap picture1;
    private Bitmap picture2;
    private Bitmap picture3;
    private String starNum;     //点赞人数
    private String commentNum;  //评论人数
    private String isStar; //是否已点赞
    private String id;  //post的唯一标识符
    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Bitmap getPicture1() {
        return picture1;
    }

    public void setPicture1(Bitmap picture1) {
        this.picture1 = picture1;
    }

    public Bitmap getPicture2() {
        return picture2;
    }

    public void setPicture2(Bitmap picture2) {
        this.picture2 = picture2;
    }

    public Bitmap getPicture3() {
        return picture3;
    }

    public void setPicture3(Bitmap picture3) {
        this.picture3 = picture3;
    }

    public String getStarNum() {
        return starNum;
    }

    public void setStarNum(String starNum) {
        this.starNum = starNum;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String isStar() {
        return isStar;
    }

    public void setStar(String star) {
        isStar = star;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPictureNum() {
        return pictureNum;
    }

    public void setPictureNum(String pictureNum) {
        this.pictureNum = pictureNum;
    }

    @Override
    public String toString() {
        return "PostBean{" +
                "icon=" + icon +
                ", userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                ", pictureNum='" + pictureNum + '\'' +
                ", picture1=" + picture1 +
                ", picture2=" + picture2 +
                ", picture3=" + picture3 +
                ", starNum='" + starNum + '\'' +
                ", commentNum='" + commentNum + '\'' +
                ", isStar=" + isStar +
                ", id='" + id + '\'' +
                '}';
    }
}
