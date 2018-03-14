package com.jk.service;

import com.jk.model.CustomerBean;

import java.util.List;

public interface UserService {

    Integer getTodayBirthdayUserCount();

    List<CustomerBean> getTodayBirthdayUserByPage(Integer page, Integer rows);

    void sendTodayBirthday(List<CustomerBean> list);
}
