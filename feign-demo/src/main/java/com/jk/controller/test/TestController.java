package com.jk.controller.test;

import com.jk.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "test")
    public String getCount() {
        int count = testService.getCount();
        System.out.println(count);
        return "indexs";
    }



}
