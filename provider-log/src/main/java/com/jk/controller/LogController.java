package com.jk.controller;

import com.alibaba.fastjson.JSON;
import com.jk.common.Constantlont;
import com.jk.model.LogBean;
import com.jk.model.ResultBean;
import com.jk.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "log")
@RefreshScope
public class LogController {

    @Autowired
    private LogService logService;

    @Value("${content}")
    private String content;

    @RequestMapping(value = "test")
    public String test(){
        return content;
    }


    /**
     * 添加日志
     * @param log
     * @return
     */
    @RequestMapping(value = "addLog")
    public Boolean addLog(@RequestParam String log){
        try{
            LogBean logBean = JSON.parseObject(log, LogBean.class);
            logService.addLog(logBean);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 查询日志记录
     * @param page  当前页（必填）
     * @param rows  每页多少条（必填）
     * @return
     */
    @RequestMapping(value = "getLogList")
    public ResultBean getLogList(@RequestParam("page") Integer page,@RequestParam("rows") Integer rows){

        ResultBean resultBean = new ResultBean();
        List<LogBean> logList = logService.getLogList(page, rows);
        long count = logService.getCount();
        resultBean.setCode(Constantlont.SUCCESS);
        resultBean.setMsg("成功");
        resultBean.setCount(count);
        resultBean.setData(logList);
        return resultBean;
    }

}
