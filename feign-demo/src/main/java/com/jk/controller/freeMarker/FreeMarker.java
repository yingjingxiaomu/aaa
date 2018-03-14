package com.jk.controller.freeMarker;

import com.jk.model.cv.*;
import com.jk.service.user.UserService;
import com.lowagie.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "free")
public class FreeMarker {

    @Autowired
    private UserService userService;

    /**
     * 转html静态页面
     * @return
     */
    @RequestMapping(value = "freeMarkerHtml")
    @ResponseBody
    public Boolean freeMarkerHtml(){

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
        map.put("titel", "简历");
        map.put("userList",userList);
        map.put("educationexperienceList",educationexperienceList);
        map.put("projectList",projectList);
        map.put("skillList",skillList);
        map.put("workexperienceList",workexperienceList);
        try {
            // 找到模板文件*.ftl
            Configuration freemarker_cfg = new Configuration();
            String ftlpath = "D:\\freemarker\\";
            String ftlname = "resume.ftl";
          /*  String ftlname = "freeMarker.ftl";*/
            freemarker_cfg.setDirectoryForTemplateLoading(new File(ftlpath));
            Template template = freemarker_cfg.getTemplate(ftlname);
            // 合并模板文件以及数据将其进行输出
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\freemarker\\myFreemarker.html"), "utf-8"));
            //进行处理
            template.process(map, out);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * ftl转word
     * @return
     */
    @RequestMapping("freeMarkerWord")
    @ResponseBody
    public Boolean freeMarkerWord(){

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
        try {
            // 找到模板文件*.ftl
            Configuration freemarker_cfg = new Configuration();
            String ftlpath = "D:\\freemarker\\";
            String ftlname = "freeMarkerWord.ftl";
          /*  String ftlname = "freeMarker.ftl";*/
            freemarker_cfg.setDirectoryForTemplateLoading(new File(ftlpath));
            Template template = freemarker_cfg.getTemplate(ftlname);
            // 合并模板文件以及数据将其进行输出
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\freemarker\\myFreemarkerWord.doc"), "utf-8"));
            //进行处理
            template.process(map, out);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 转pdf页面
     * @return
     */
    @RequestMapping(value = "freeMarkerPdf")
    @ResponseBody
    public Boolean freeMarkerPdf() throws IOException, TemplateException {

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
        String outputFile = "D:\\freemarker\\contractTemplate.pdf";
        OutputStream os = new FileOutputStream(outputFile);

        ITextRenderer renderer = new ITextRenderer();

        renderer.setDocument(url);

        // 解决中文问题
        ITextFontResolver fontResolver = renderer.getFontResolver();
        try {
           /* simsun.ttc*/
            fontResolver.addFont("D:\\freemarker\\wordFont\\simsun.ttc", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
           /* fontResolver.addFont("D:\\freemarker\\wordFont\\simsun.ttc", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            fontResolver.addFont("D:\\freemarker\\wordFont\\simsun.ttc", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);*/
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
        return true;
    }

  /*  public static void main(String[] args) throws IOException, TemplateException {

        // 制造数据
        Map<String, String> map = new HashMap<String, String>();
        map.put("titel", "简历");

        // 找到模板文件*.ftl
        Configuration freemarker_cfg = new Configuration();
        String ftlpath = "D:\\freemarker\\";
        String ftlname = "freeMarker.ftl";
        freemarker_cfg.setDirectoryForTemplateLoading(new File(ftlpath));
        Template template = freemarker_cfg.getTemplate(ftlname);

        // 合并模板文件以及数据将其进行输出
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\freemarker\\myFreemarker.html"), "utf-8"));

        //进行处理
        template.process(map, out);


    }
*/
}
