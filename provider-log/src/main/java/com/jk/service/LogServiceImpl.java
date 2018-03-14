package com.jk.service;

import com.jk.mapper.LogMapper;
import com.jk.model.LogBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public void addLog(LogBean logBean) {
        logMapper.addLog(logBean);
    }

    @Override
    public List<LogBean> getLogList(Integer page, Integer rows) {
        Integer skip = 0;
        Integer limit = 5;
        skip = (page-1)*rows;
        limit = rows;
        return logMapper.getLogList(skip,limit);
    }

    @Override
    public long getCount() {
        return logMapper.getCount();
    }
}
