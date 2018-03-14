package com.jk.service.user;

import com.alibaba.fastjson.JSONObject;
import com.jk.mapper.user.UserMapper;
import com.jk.model.cv.*;
import com.jk.model.log.loginUser;
import com.jk.model.shiro.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUser(String userId) {

        return userMapper.getUser(userId);
    }

    @Override
    public List<Educationexperience> getEducationexperience(String userId) {
        return userMapper.getEducationexperience(userId);
    }

    @Override
    public List<Project> getProject(String userId) {
        return userMapper.getProject(userId);
    }

    @Override
    public List<Skill> getSkill(String userId) {
        return userMapper.getSkill(userId);
    }

    @Override
    public List<Workexperience> getWorkexperience(String userId) {
        return userMapper.getWorkexperience(userId);
    }

    @Override
    public String getUserByPageList(Integer page, Integer rows) {
        page =(page-1)*rows;
        long total = userMapper.getUserList().size();
        List<User> list = userMapper.getUserByPageList(page,rows);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("count", total);
        jsonObject.put("data", list);

        return jsonObject.toString();
    }

    @Override
    public void updateUserShortCode(String id, String shortCode) {
        userMapper.updateUserShortCode(id,shortCode);
    }

    @Override
    public void addCode(String shortCode) {
        Date date = new Date();
        Long newtime = date.getTime()+(60*1000);
        Code code = new Code();
        code.setId(shortCode);
        code.setGenerationTime(date);
        code.setEffectiveTime(new Date(newtime));
        userMapper.addCode(code);
    }

    @Override
    public Object queryUserShortCode(String id) {
        return userMapper.queryUserShortCode(id);
    }

    @Override
    public Object queryShortCode(String shortCode) {
        return userMapper.queryShortCode(shortCode);
    }

    @Override
    public UserInfo findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

}
