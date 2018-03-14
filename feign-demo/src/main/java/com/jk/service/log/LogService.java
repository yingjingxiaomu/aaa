package com.jk.service.log;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@FeignClient(value = "provider-log",fallback = LogServiceHystric.class)
public interface LogService {

    @RequestMapping("/log/addLog")
    void addLog(@RequestParam(value = "log") String log);

    @RequestMapping("/log/getLogList")
    @ResponseBody
    String getLogList(@RequestParam(value = "page")Integer page,@RequestParam(value = "rows") Integer rows);
}
