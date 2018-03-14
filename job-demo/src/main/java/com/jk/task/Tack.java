package com.jk.task;

import com.jk.model.CustomerBean;
import com.jk.pool.ThreadPool;
import com.jk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class Tack {

    @Autowired
    private UserService userService;

    @Scheduled(cron = "1 45 8 * * ?")
    public void birthday(){
        System.out.println("定时器启动了！！！！！！"+new Date());
        Integer rows = 1000;
        //总共要查询的页数
        Integer userInfoCount = userService.getTodayBirthdayUserCount();
        Integer count;
        if(userInfoCount % rows == 0){
            count = userInfoCount / rows;
        }else{
            count = userInfoCount / rows + 1;
        }

        for(Integer page = 1;page <= count; page++){
            List<CustomerBean> list = userService.getTodayBirthdayUserByPage(page,rows);
            ThreadPool.executeFixedThread(new TackThread(userService,list));
        }

    }
}
