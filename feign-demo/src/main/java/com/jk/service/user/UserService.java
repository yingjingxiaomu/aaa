package com.jk.service.user;

import com.jk.model.cv.*;
import com.jk.model.log.loginUser;
import com.jk.model.shiro.UserInfo;

import java.util.List;

public interface UserService {

    List<User> getUser(String userId);

    List<Educationexperience> getEducationexperience(String userId);

    List<Project> getProject(String userId);

    List<Skill> getSkill(String userId);

    List<Workexperience> getWorkexperience(String userId);

    String getUserByPageList(Integer page, Integer rows);

    void updateUserShortCode(String id, String shortCode);

    void addCode(String shortCode);

    Object queryUserShortCode(String id);

    Object queryShortCode(String shortCode);

    UserInfo findByUsername(String username);
}
