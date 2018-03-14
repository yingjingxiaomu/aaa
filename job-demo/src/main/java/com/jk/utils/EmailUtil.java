package com.jk.utils;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmailUtil {

    private static JavaMailSenderImpl mailSender = createMailSender();

    /**
     * 邮件发送器
     *
     * @return 配置好的工具
     */
    private static JavaMailSenderImpl createMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(ConBean.HOST);
        sender.setPort(ConBean.PORT);
        sender.setUsername(ConBean.USERNAME);
        sender.setPassword(ConBean.PASSWORD);
        sender.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", "25000");
        p.setProperty("mail.smtp.auth", "true");
        sender.setJavaMailProperties(p);
        return sender;
    }

    /**
     * 发送邮件
     *
     * @param to 接受人
     * @param subject 主题
     * @param html 发送内容
     * @throws MessagingException 异常
     * @throws UnsupportedEncodingException 异常
     */
    public static void sendHtmlMail(String to, String subject, String html) throws MessagingException,UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(ConBean.EMAILFORM, ConBean.USERNAME);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(html, true);
        mailSender.send(mimeMessage);
    }

    /**
     * 发送邮件带附件
     * @param to
     * @param subject
     * @param html
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public static void sendHtmlAndFailMail(String to, String subject, String html,List<File> file) throws MessagingException,UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(ConBean.EMAILFORM, "系统名称");
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(html, true);
        for (File file1 : file) {
            messageHelper.addAttachment(file1.getName(),file1);
        }
        mailSender.send(mimeMessage);


    }

    public static void main(String[] args) throws UnsupportedEncodingException, MessagingException {
        //EmailUtil.sendHtmlMail("cuiliyingadmin@163.com","2323232","<h1 style='color:red'>哈哈，金泰你我爱你！！！</<h1>");

        List<File> files = new ArrayList<File>();
        for (int i = 0; i < 5; i++) {
            files.add(new File("E:\\16.jpg"));
        }
        EmailUtil.sendHtmlAndFailMail("18335012187@163.com","2323232","<h1 style='color:red'>哈哈，fdsfsf金泰你我爱你！！！</<h1>",files);

    }



}
