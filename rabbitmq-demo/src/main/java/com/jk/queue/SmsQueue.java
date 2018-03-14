package com.jk.queue;

import com.fasterxml.jackson.databind.JsonNode;
import com.jk.utils.*;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RabbitListener(queues = "sss")
public class SmsQueue {

    @RabbitHandler
    public void process(String hash) {
        System.out.println(hash);
        try {
            JsonNode jsonNode = JsonUtil.jsonToJsonNode(hash);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("accountSid", ConBean.ACCOUNT_SID);
            params.put("templateid", ConBean.TMPLATE_ID);
            params.put("param", jsonNode.get("content").asText());
            params.put("to", jsonNode.get("phoneNumber").asText());
            params.put("timestamp", TimeUtil.dateTostr(new Date(), "yyyyMMddHHmmss"));
            params.put("sig", Md5Util.getMd532(params.get("accountSid").toString() + ConBean.TO_KEN + params.get("timestamp").toString()));
            HttpClient.post(ConBean.SMS_URL, params);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
