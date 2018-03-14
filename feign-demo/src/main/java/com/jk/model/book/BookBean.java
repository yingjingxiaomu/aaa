package com.jk.model.book;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookBean implements Serializable {

   private Integer id;
   //书名
   private String name;
   //是否上下架  单选
    private String putaway;
   //类型 下拉
   private String type;
   //简介 富文本
   private String info;
    //图片
   private String heading;
    //适应人群
   private String people;

   //时间
   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   private Date ctime;


    //时间格式转换
    public String getCtime() {

        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(ctime==null){
            return null;
        }

        return sim.format(ctime);

    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPutaway() {
        return putaway;
    }

    public void setPutaway(String putaway) {
        this.putaway = putaway;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }
}
