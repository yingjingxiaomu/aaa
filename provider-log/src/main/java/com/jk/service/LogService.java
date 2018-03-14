package com.jk.service;

import com.jk.model.LogBean;

import java.util.List;

public interface LogService {

    void addLog(LogBean logBean);

    List<LogBean> getLogList(Integer page, Integer rows);

    long getCount();
}
