package com.jk.controller.user;

import com.jk.model.cv.*;
import com.jk.service.user.UserService;
import com.jk.utils.ConBean;
import com.jk.utils.FileUtil;
import com.jk.utils.QrCodeUtil;
import com.lowagie.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 跳转查询用户
     * @return
     */
    @RequestMapping(value = "toUserHtml")
    public String toBookHtml(){
        return "user/show_user";
    }


    /**
     * 分页查询用户方法
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "getUserList")
    @ResponseBody
    public String getBookList(Integer page, Integer rows){
        String userlist=userService.getUserByPageList(page,rows);
        return  userlist;

    }
    @RequestMapping(value = "getQrCode")
    @ResponseBody
    public Map<String,Object> getQrCode(String content,String id){
        Map<String, Object> map = new HashMap<String, Object>();
        String shortCode = "";
        //12位uuid
        for (int i = 0; i < 5; i++) {
            String s = UUID.randomUUID().toString();
            s =  s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
            shortCode = s.substring(0, 12);
        }
        try {
            userService.updateUserShortCode(id,shortCode);
            userService.addCode(shortCode);
            QrCodeUtil.generatingTwoDimensionalCode(content,shortCode);
            map.put("path", ConBean.IMG_SERVER_PATH+shortCode+".png");
            map.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success",false);
        }
        return  map;
    }

    /**
     * 测试二维码一分钟过期
     * @param id
     * @return
     */
    @RequestMapping(value = "toUserADDList")
    public String toUserADDList(String id){
        String path = "";
        try{
            /* 创建数据模型 */
            String userId = "1";
            //个人简介
            List<User> userList = userService.getUser(userId);
            //教育经历
            List<Educationexperience> educationexperienceList =   userService.getEducationexperience(userId);
            //项目
            List<Project> projectList =   userService.getProject(userId);
            //技能专长
            List<Skill> skillList =   userService.getSkill(userId);
            //工作经历
            List<Workexperience> workexperienceList =   userService.getWorkexperience(userId);
            // 制造数据
            Map<Object,Object> map = new HashMap<Object, Object>();
            map.put("titel", "简        历");
            map.put("userList",userList);
            map.put("educationexperienceList",educationexperienceList);
            map.put("projectList",projectList);
            map.put("skillList",skillList);
            map.put("workexperienceList",workexperienceList);
            /* 创建配置 */
            Configuration cfg = new Configuration();
            /* 指定模板存放的路径 */
            cfg.setDirectoryForTemplateLoading(new File("D:\\freemarker\\"));
            cfg.setDefaultEncoding("UTF-8");
            /* 从上面指定的模板目录中加载对应的模板文件 */
            Template temp = cfg.getTemplate("resumepdf.ftl");

            /* 将生成的内容写入hello .html中 */

            String file1 = "D:\\freemarker\\myFreemarkerPdf.html";
            File file = new File(file1);
            if (!file.exists())
                file.createNewFile();
            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), "UTF-8"));
            temp.process(map, out);
            out.flush();

            String url = new File(file1).toURI().toURL().toString();
            String outputFile = ConBean.IMG_SERVER_PATH_PHONR+"contractTemplate.pdf";
            OutputStream os = new FileOutputStream(outputFile);

            ITextRenderer renderer = new ITextRenderer();

            renderer.setDocument(url);

            // 解决中文问题
            ITextFontResolver fontResolver = renderer.getFontResolver();
            try {
                fontResolver.addFont("D:\\freemarker\\wordFont\\simsun.ttc", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            } catch (Exception e) {
                e.printStackTrace();
            }
               /* fontResolver.addFont("C:/Windows/Fonts/ARIALUNI.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);*/
            renderer.layout();
            try {
                renderer.createPDF(os);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("转换成功！");
            os.close();

            path = "";
        }catch (Exception e){
            e.printStackTrace();
        }


        User user = (User)userService.queryUserShortCode(id);
        String shortCode = user.getShortCode();
        Code code = (Code)userService.queryShortCode(shortCode);

        Date date = new Date();
        Long time = date.getTime(); // 当前时间毫秒值
        Long time1 = code.getEffectiveTime().getTime(); //二维码过期时间毫秒值

        if(time<time1){
          /*  return "user/show_pdf";*/
            return "redirect:/contractTemplate.pdf";
        }else{
            return "user/error";
        }

    }

    // 登录提交地址和applicationontext-shiro.xml配置的loginurl一致。 (配置文件方式的说法)
    @RequestMapping(value="login")
    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception {
        System.out.println("HomeController.login()");
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");

        System.out.println("exception=" + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            }else if(IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            }else if("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
        }

        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理.
        return "login";
    }

    @RequestMapping(value = "toIndex")
    public String toIndex(){
        return "indexs";
    }

}
