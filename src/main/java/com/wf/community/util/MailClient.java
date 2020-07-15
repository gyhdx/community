package com.wf.community.util;

import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/7/13 11:33
 */
@Component
public class MailClient {

    private static final Logger log = LoggerFactory.getLogger(MailClient.class);

//    @Resource
//    private JavaMailSender mailSender;
//
//    @Value("${spring.mail.username}")
//    private String from;
//
//    public void sendMail(String to , String subject , String context){
//        try {
//            MimeMessage mimeMessage = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
//            helper.setFrom(from);
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(context , true);
//            mailSender.send(helper.getMimeMessage());
//        } catch (MessagingException e) {
//            log.info("邮件发送失败：" + e.getMessage());
//        }
//    }

    public static void sendEmail(String sendTo,String yzm) throws Exception {
        HtmlEmail email = new HtmlEmail();
        //邮箱的SMTP服务器，一般123邮箱的是smtp.123.com,qq邮箱为smtp.qq.com
        email.setHostName("smtp.163.com");
        //设置发送的字符类型
        email.setCharset("utf-8");
        //设置收件人
        email.addTo(sendTo);
        //发送人的邮箱为自己的，用户名可以随便填
        email.setFrom("gyhdxwang@163.com","wf");
        //设置发送人到的邮箱和用户名和授权码(授权码是自己设置的)
        email.setAuthentication("gyhdxwang@163.com","gx19960718");
        //设置发送主题
        email.setSubject("激活账号");
        //设置发送内容
        email.setMsg(yzm);
        //进行发送
        email.send();

    }

    public static String YZMUtil(){
        String yzm = "";
        for (int i = 0 ; i < 4 ; i ++) {
            yzm = yzm + String.valueOf((int) Math.floor(Math.random() * 9 + 1));
        }
        return yzm;
    }
}