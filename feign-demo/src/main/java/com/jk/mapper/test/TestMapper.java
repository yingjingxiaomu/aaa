package com.jk.mapper.test;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Mapper
@Component
public interface TestMapper {

    @Select("SELECT count(1) from test")
    int getCount();

}
