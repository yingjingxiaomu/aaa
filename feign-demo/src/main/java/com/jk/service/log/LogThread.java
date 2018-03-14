package com.jk.service.log;


import com.alibaba.fastjson.JSON;
import com.jk.model.log.LogBean;

public class LogThread implements Runnable {

    private LogService logService;

    private LogBean logBean;

    public LogThread(LogService loginService, LogBean logBean) {

        String log = JSON.toJSONString(logBean);
        loginService.addLog(log);
    }

    public LogThread() {
    }

    public void run() {

    }

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public LogBean getLogBean() {
        return logBean;
    }

    public void setLogBean(LogBean logBean) {
        this.logBean = logBean;
    }
}
