package com.jk.service.test;


import com.jk.mapper.test.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    public int getCount() {
        return testMapper.getCount();
    }

}
