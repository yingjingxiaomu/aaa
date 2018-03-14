package com.jk.controller.log;

import com.alibaba.fastjson.JSON;
import com.jk.model.log.LogBean;
import com.jk.model.log.loginUser;
import com.jk.pool.ThreadPool;
import com.jk.service.log.LogService;
import com.jk.service.log.LogThread;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;

@Controller
@RequestMapping(value = "log")
public class LogController {

    @Autowired
    private LogService logService;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @RequestMapping(value = "sendSms")
    @ResponseBody
    public Boolean sendSms() {
        String phoneNumber = "18902138915";
        int random = (int)((Math.random()*9+1)*100000);
        HashMap<String,Object> hash = new HashMap<String, Object>();
        hash.put("phoneNumber",phoneNumber);
        hash.put("content",random);
        this.rabbitTemplate.convertAndSend("sss",  JSON.toJSONString(hash));
        return true;
    }

    @RequestMapping(value = "toLoginList")
    public String toLoginList(){

        return "log/show_log";
    }

    /**
     * 添加日志
     */
    @RequestMapping(value = "loginfo")
    @ResponseBody
    public void loginfo(){
        String username = "99999";
        String password = "99999";
        LogBean logBean = new LogBean();
        logBean.setLoginNumber("徐子泷");
        logBean.setLoginIp("127.0.0.1");
        logBean.setLoginIpLocation("局域网");
        logBean.setLoginStatus("登录成功");
        logBean.setLoginTime(BeiJingTimeXZL());
        logBean.setRequestMsg("账号："+username+"密码"+password);
        logBean.setResponseMsg("{'账号':'admin','密码':'admin'}");
        ThreadPool.executeFixedThread(new LogThread(logService,logBean));
    }

    /**
     * 存mongodb转换时间
     * @return
     */
    public Date BeiJingTimeXZL(){
        Date time = new Date();
        Long newtime = time.getTime()+(8*60*60*1000);
        return new Date(newtime);
    }

    /**
     * 查询日志
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "getLogList")
    @ResponseBody
    public String getLogList(Integer page,Integer rows){
        String s = logService.getLogList(page,rows);
         return s;
    }

    /**
     * loginUser
     */
  /*  @RequestMapping("/loginUser")
    public String loginUser(String username,String password,HttpSession session) {

        String resource = "spring-shiro.xml";
        ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext(resource);
        org.apache.shiro.mgt.SecurityManager securityManager = (org.apache.shiro.mgt.SecurityManager)appCtx.getBean("securityManager");
        SecurityUtils.setSecurityManager(securityManager);

        SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        try {
            subject.login(token);   //完成登录
            loginUser user=(loginUser) subject.getPrincipal();
            session.setAttribute("user", user);
            return "index";
        } catch(Exception e) {
            return "login";//返回登录页面
        }

    }*/

}
