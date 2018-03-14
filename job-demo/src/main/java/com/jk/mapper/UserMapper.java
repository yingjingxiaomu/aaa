package com.jk.mapper;

import com.jk.model.CustomerBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

    @Select("SELECT COUNT(*) FROM t_customer")
    Integer getTodayBirthdayUserCount();

    @Select("SELECT * FROM t_customer WHERE DATE_FORMAT(NOW(),'%m-%d')=FROM_UNIXTIME(UNIX_TIMESTAMP(CAST(SUBSTRING(identityCard,7,8) AS DATETIME)),'%m-%d') LIMIT #{page},#{rows}")
    List<CustomerBean> getTodayBirthdayUserByPage(@Param("page")Integer page,@Param("rows") Integer rows);
}
