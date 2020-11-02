package com.example.happylearning.Bean;

public class HomeWorkList {


    private String type;
    private String title;
    private String public_time;
    private String ID;
    private String isSubmit;
    private String file_name;
    private String file_path;

    private String limit_time;
    private String content;




    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getPublic_time() {
        return public_time;
    }

    public void setPublic_time(String public_time) {
        this.public_time = public_time;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLimit_time() {
        return limit_time;
    }

    public void setLimit_time(String limit_time) {
        this.limit_time = limit_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsSubmit() {
        return isSubmit;
    }

    public void setIsSubmit(String isSubmit) {
        this.isSubmit = isSubmit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "HomeWorkList{" +
                "type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", public_time='" + public_time + '\'' +
                ", ID='" + ID + '\'' +
                ", isSubmit='" + isSubmit + '\'' +
                ", file_name='" + file_name + '\'' +
                ", limit_time='" + limit_time + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}