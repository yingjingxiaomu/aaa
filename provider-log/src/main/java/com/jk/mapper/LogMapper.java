package com.jk.mapper;

import com.jk.model.LogBean;

import java.util.List;

public interface LogMapper {

    void addLog(LogBean logBean);

    List<LogBean> getLogList(Integer skip, Integer limit);

    long getCount();
}
