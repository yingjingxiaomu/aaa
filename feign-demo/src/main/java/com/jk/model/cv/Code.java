package com.jk.model.cv;


import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Code implements Serializable{

  private String id;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date generationTime;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date effectiveTime;

  private String codeImage;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public Date getGenerationTime() {
    return generationTime;
  }

  public void setGenerationTime(Date generationTime) {

    this.generationTime = generationTime;
  }

  public Date getEffectiveTime() {
    return effectiveTime;
  }

  public void setEffectiveTime(Date effectiveTime) {
    this.effectiveTime = effectiveTime;
  }

  public String getCodeImage() {
    return codeImage;
  }

  public void setCodeImage(String codeImage) {
    this.codeImage = codeImage;
  }
}
