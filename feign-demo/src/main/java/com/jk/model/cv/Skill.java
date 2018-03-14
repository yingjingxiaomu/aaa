package com.jk.model.cv;

import java.io.Serializable;

/**
 * 技能专长表
 */
public class Skill implements Serializable{
    /**
     * 主键Id
     */
    private String id;
    /**
     * 技能专长表
     */
    private String skill;
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

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id='" + id + '\'' +
                ", skill='" + skill + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}