package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void sendSimpleMail() throws Exception {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("pengjunzhenwork@163.com");
        message.setTo("1379197745@qq.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");
        mailSender.send(message);
    }

    @Test
    public void sendAttachmentsMail() throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("pengjunzhenwork@163.com");
        helper.setTo("1379197745@qq.com");
        helper.setSubject("主题：有附件");
        helper.setText("有附件的邮件");

        FileSystemResource file = new FileSystemResource(new File("photo.jpg"));
        helper.addAttachment("附件-1.jpg", file);
        helper.addAttachment("附件-2.jpg", file);

        mailSender.send(mimeMessage);

    }

    @Test
    public void sendInlineMail() throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("pengjunzhenwork@163.com");
        helper.setTo("1379197745@qq.com");
        helper.setSubject("主题：嵌入静态资源");
        helper.setText("<html><body>正文包含图片<br /><img src=\"cid:photo\" ></body></html>", true);

        FileSystemResource file = new FileSystemResource(new File("photo.jpg"));
        helper.addInline("photo", file);
//        这里需要注意的是addInline函数中资源名称weixin需要与正文中cid:weixin对应起来

        FileSystemResource file1 = new FileSystemResource(new File("photo.jpg"));
        helper.addAttachment("附件-1.jpg", file1);
        mailSender.send(mimeMessage);
    }
}

