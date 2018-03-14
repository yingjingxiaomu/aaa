package com.jk.model;

import java.io.Serializable;

public class CustomerBean implements Serializable{

  private String id;

  private String name;

  private Integer sex;

  private String phoneNumber;

  private String password;

  private String email;

  private String identityCard;

  private String faceCard;

  private String rearCard;

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

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getIdentityCard() {
    return identityCard;
  }

  public void setIdentityCard(String identityCard) {
    this.identityCard = identityCard;
  }

  public String getFaceCard() {
    return faceCard;
  }

  public void setFaceCard(String faceCard) {
    this.faceCard = faceCard;
  }

  public String getRearCard() {
    return rearCard;
  }

  public void setRearCard(String rearCard) {
    this.rearCard = rearCard;
  }

  @Override
  public String toString() {
    return "CustomerBean{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", sex=" + sex +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", password='" + password + '\'' +
            ", email='" + email + '\'' +
            ", identityCard='" + identityCard + '\'' +
            ", faceCard='" + faceCard + '\'' +
            ", rearCard='" + rearCard + '\'' +
            '}';
  }
}
