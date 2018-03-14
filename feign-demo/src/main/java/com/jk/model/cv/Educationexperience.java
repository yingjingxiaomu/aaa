package com.jk.model.cv;

import java.io.Serializable;

/**
 * 教育经历表
 */
public class Educationexperience implements Serializable {
    /**
     * 主键Id
     */
    private String id;
    /**
     * 教育起始时间(XXXX-XXXX)
     */
    private String time;
    /**
     * 学校
     */
    private String school;
    /**
     * 学历
     */
    private String educationBackground;
    /**
     * 用户Id
     */
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEducationBackground() {
        return educationBackground;
    }

    public void setEducationBackground(String educationBackground) {
        this.educationBackground = educationBackground;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Educationexperience{" +
                "id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", school='" + school + '\'' +
                ", educationBackground='" + educationBackground + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}