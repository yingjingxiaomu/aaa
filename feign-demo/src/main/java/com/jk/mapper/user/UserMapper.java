package com.jk.mapper.user;

import com.jk.model.cv.*;
import com.jk.model.log.loginUser;
import com.jk.model.shiro.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

    //个人简介
    @Select("SELECT * FROM t_user WHERE id = #{userId}")
    List<User> getUser(@Param("userId")String userId);

    //教育经历
    @Select("SELECT e.time,e.school,e.educationBackground FROM t_user u,t_educationexperience e WHERE e.userId = u.id AND u.id = #{userId} ")
    List<Educationexperience> getEducationexperience(@Param("userId")String userId);

    //项目
    @Select("SELECT p.id,p.title,p.developmentEnvironment,p.projectFramework,p.itemDescription,p.jobDuty,p.functionModule FROM t_user u,t_project p WHERE p.userId = u.id AND u.id = #{userId} ")
    List<Project> getProject(@Param("userId")String userId);

    //技能专长
    @Select("SELECT s.id,s.skill FROM t_user u,t_skill s WHERE s.userId = u.id AND u.id = #{userId}")
    List<Skill> getSkill(@Param("userId")String userId);

    //工作经历
    @Select("SELECT w.time,w.company,w.particulars FROM t_user u,t_workexperience w WHERE w.userId = u.id AND u.id = #{userId} ")
    List<Workexperience> getWorkexperience(@Param("userId") String userId);

    //查询用户
    @Select("SELECT * FROM t_user")
    List<User> getUserList();

    //查询用户分页
    @Select("SELECT * FROM t_user LIMIT #{page},#{rows}")
    List<User> getUserByPageList(@Param("page")Integer page,@Param("rows") Integer rows);

    //修改短码
    @Update("UPDATE t_user set shortCode = #{shortCode} where id = #{id}")
    void updateUserShortCode(@Param("id")String id,@Param("shortCode") String shortCode);

    //添加短码关联数据
    @Insert("insert into t_code(id,generationTime,effectiveTime) values(#{id},#{generationTime},#{effectiveTime})")
    void addCode(Code code);

    //查询短码时间
    @Select("SELECT * FROM t_code WHERE id=#{shortCode}")
    Code queryShortCode(@Param("shortCode") String shortCode);

    //查询用户短码
    @Select("SELECT * FROM t_user WHERE id = #{id}")
    User queryUserShortCode(@Param("id")String id);

    //登录查询用户返回单条记录
    @Select("SELECT * FROM user_info where username = #{username}")
    UserInfo findByUsername(@Param("username")String username);
}
