package com.jk.model.cv;

import java.io.Serializable;

/**
 * 工作经验表
 */
public class Workexperience implements Serializable{
    private String id;

    private String time;

    private String company;

    private String particulars;

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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Workexperience{" +
                "id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", company='" + company + '\'' +
                ", particulars='" + particulars + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}