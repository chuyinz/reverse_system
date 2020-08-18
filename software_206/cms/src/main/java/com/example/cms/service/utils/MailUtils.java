package com.example.cms.service.utils;

import java.util.Date;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * 发送邮件
 * @author xzy
 * @date 2018-12-24
 */
public class MailUtils {

    public static void sendEmail(String toEmailAddress,String emailTitle, String content)throws Exception{
        //设置邮件服务器
        Properties properties = new Properties();
        //可以设置邮件服务器
        properties.setProperty("mail.host","smtp.163.com");
        properties.setProperty("mail.smtp.auth","true");
        //与邮件服务器的连接
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("gz7271391@163.com","mima123");
            }
        });

        //发送的消息，基于观察者模式进行设计的
        Message msg = new MimeMessage(session);
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmailAddress));
        msg.setSubject(emailTitle);
        //使用StringBuilder，因为StringBuilder加载速度会比String快，而且线程安全性也不错
        StringBuilder builder = new StringBuilder();
        builder.append("原密码："+"\n"+content);
        builder.append("\n时间 " + new Date());
        msg.setText(builder.toString());
        msg.setFrom(new InternetAddress("gz7271391@163.com"));
        //发送消息
        Transport.send(msg);
    }
}


