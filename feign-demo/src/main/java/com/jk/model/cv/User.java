package com.jk.model.cv;
/**
 * 用户表
 */

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Serializable{
    /**
     * 主键Id
     */
    private String id;
    /**
     *姓名
     */
    private String name;
    /**
     *性别
     */
    private Integer sex;
    /**
     *出生年月
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    /**
     *籍贯
     */
    private String address;
    /**
     *年龄
     */
    private Integer age;
    /**
     *工作年限
     */
    private Integer workAge;
    /**
     *学历
     */
    private String education;
    /**
     *专业
     */
    private String major;
    /**
     *移动电话
     */
    private String phone;
    /**
     *电子邮箱
     */
    private String email;
    /**
     *工作性质
     */
    private String jobCategory;
    /**
     *目标职位
     */
    private String targetPosition;
    /**
     *期望薪资
     */
    private String expectedSalary;
    /**
     *工作地
     */
    private String workSpace;
    /**
     *自我评价
     */
    private String selfAssessment;

    /**
     * 头像
     */
    private String heading;

    /**
     * 短编码
     */
    private String shortCode;

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        if(birthday == null){
            return null;
        }
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        return s.format(birthday);
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWorkAge() {
        return workAge;
    }

    public void setWorkAge(Integer workAge) {
        this.workAge = workAge;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    public String getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(String targetPosition) {
        this.targetPosition = targetPosition;
    }

    public String getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(String expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public String getWorkSpace() {
        return workSpace;
    }

    public void setWorkSpace(String workSpace) {
        this.workSpace = workSpace;
    }

    public String getSelfAssessment() {
        return selfAssessment;
    }

    public void setSelfAssessment(String selfAssessment) {
        this.selfAssessment = selfAssessment;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", workAge=" + workAge +
                ", education='" + education + '\'' +
                ", major='" + major + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", jobCategory='" + jobCategory + '\'' +
                ", targetPosition='" + targetPosition + '\'' +
                ", expectedSalary='" + expectedSalary + '\'' +
                ", workSpace='" + workSpace + '\'' +
                ", selfAssessment='" + selfAssessment + '\'' +
                '}';
    }
}