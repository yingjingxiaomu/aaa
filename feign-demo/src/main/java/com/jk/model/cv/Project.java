package com.jk.model.cv;

import java.io.Serializable;

/**
 * 项目描述表
 */
public class Project implements Serializable{
    /**
     * 主键Id
     */
    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 开发环境
     */
    private String developmentEnvironment;
    /**
     * 所用技术
     */
    private String projectFramework;
    /**
     * 项目描述
     */
    private String itemDescription;
    /**
     * 责任描述
     */
    private String jobDuty;
    /**
     * 功能模块
     */
    private String functionModule;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDevelopmentEnvironment() {
        return developmentEnvironment;
    }

    public void setDevelopmentEnvironment(String developmentEnvironment) {
        this.developmentEnvironment = developmentEnvironment;
    }

    public String getProjectFramework() {
        return projectFramework;
    }

    public void setProjectFramework(String projectFramework) {
        this.projectFramework = projectFramework;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getJobDuty() {
        return jobDuty;
    }

    public void setJobDuty(String jobDuty) {
        this.jobDuty = jobDuty;
    }

    public String getFunctionModule() {
        return functionModule;
    }

    public void setFunctionModule(String functionModule) {
        this.functionModule = functionModule;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}