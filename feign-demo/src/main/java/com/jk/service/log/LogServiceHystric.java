package com.jk.service.log;

import org.springframework.stereotype.Component;

@Component
public class LogServiceHystric implements LogService{
    @Override
    public void addLog(String log) {
        System.out.println("断路器生效了");
    }

    @Override
    public String getLogList(Integer page, Integer rows) {
        return "断路器生效了";
    }
}
