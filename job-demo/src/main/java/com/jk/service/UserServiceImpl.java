package com.jk.service;

import com.jk.mapper.UserMapper;
import com.jk.model.CustomerBean;
import com.jk.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public Integer getTodayBirthdayUserCount() {

        return userMapper.getTodayBirthdayUserCount();
    }

    @Override
    public List<CustomerBean> getTodayBirthdayUserByPage(Integer page, Integer rows) {
        page = (page-1)*rows;
        return userMapper.getTodayBirthdayUserByPage(page,rows);
    }

    @Override
    public void sendTodayBirthday(List<CustomerBean> list) {

        for (int i=0;i<list.size();i++){
            try {
                HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("accountSid", ConBean.ACCOUNT_SID);
                params.put("templateid", ConBean.TMPLATE_ID);
                params.put("param",list.get(i).getName());
                params.put("to",list.get(i).getPhoneNumber());
                params.put("timestamp", TimeUtil.dateTostr(new Date(),"yyyyMMddHHmmss"));
                params.put("sig", Md5Util.getMd532(params.get("accountSid").toString()+ ConBean.TO_KEN+params.get("timestamp").toString()));
                HttpClient.post(ConBean.SMS_URL,params);
                System.out.println("已发送完第"+i+"条短信");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (int i=0;i<list.size();i++){
            try {
                ArrayList<File> files = new ArrayList<File>();
                files.add(new File("E:\\桌面图片\\happyBirthaday.jpg"));
                EmailUtil.sendHtmlAndFailMail(list.get(i).getEmail(),"徐子泷的问候","祝您生日快乐",files);
                System.out.println("已发送完第"+i+"条Email");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
