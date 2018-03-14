package com.jk.model;


import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Document(collection = "log")
public class LogBean implements Serializable{

    @Id
    private String id;

    private String loginNumber;

    /*@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm-ss")*/
    @JSONField(format = "yyyy-MM-dd HH:mm-ss")
     private Date loginTime;

     private String loginStatus;

     private String loginIp;

     private String loginIpLocation;

     private String errorInfo;

     private String requestMsg;

     private String responseMsg;

  public String getLoginTime() {
       SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(loginTime==null){
            return null;
        }
        Long newtime = loginTime.getTime()-(8*60*60*1000);
        return sim.format(new Date(newtime));
    }
     public String getLoginNumber() {
     return loginNumber;
 }

    public void setLoginNumber(String loginNumber) {
        this.loginNumber = loginNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLoginTime(Date loginTime) {

        this.loginTime =  loginTime;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginIpLocation() {
        return loginIpLocation;
    }

    public void setLoginIpLocation(String loginIpLocation) {
        this.loginIpLocation = loginIpLocation;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getRequestMsg() {
        return requestMsg;
    }

    public void setRequestMsg(String requestMsg) {
        this.requestMsg = requestMsg;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }
}
