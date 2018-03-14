package com.jk.task;


import com.alibaba.fastjson.JSON;
import com.jk.model.CustomerBean;
import com.jk.service.UserService;

import java.util.List;

public class TackThread implements Runnable {

    private UserService userService;

    private List<CustomerBean> list;

    public TackThread(UserService userService, List<CustomerBean> list) {
        userService.sendTodayBirthday(list);
    }

    public TackThread() {
    }

    public void run() {

    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<CustomerBean> getList() {
        return list;
    }

    public void setList(List<CustomerBean> list) {
        this.list = list;
    }
}
