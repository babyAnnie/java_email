package com.example.demo.controller;

import com.example.demo.domain.EmailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Controller
public class HelloController {

    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping("/")
    public String index(Model model) {

        EmailInfo emailInfo = new EmailInfo();
        model.addAttribute("emailInfo", emailInfo);
        return "index";
    }

    @RequestMapping(value = "/send-email", method = RequestMethod.POST)
    public String sendEmail(@ModelAttribute(value = "emailInfo") EmailInfo emailInfo, Model model) throws Exception {
        sendInlineMail(emailInfo.getEmailAddress(),emailInfo.getSubject(), emailInfo.getContent());
        model.addAttribute("result", "发送邮件地址：" + emailInfo.getEmailAddress() + ", 内容：" + emailInfo.getContent() + "。");
        return "success";
    }

    /**
     * 发送邮件参数
     * @param address 发送邮件地址
     * @param content 内容
     * @param subject 主题
     * @throws Exception 异常
     */
    public void sendInlineMail(String address, String subject,String content) throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("2829139244@qq.com");
        helper.setTo(address);
        helper.setSubject(subject);
        helper.setText("<html><body>" + content + "<br /><img src=\"cid:photo\" ></body></html>", true);

        FileSystemResource file = new FileSystemResource(new File("photo.jpg"));
        helper.addInline("photo", file);
//        这里需要注意的是addInline函数中资源名称weixin需要与正文中cid:weixin对应起来

        FileSystemResource file1 = new FileSystemResource(new File("test.txt"));
        helper.addAttachment("test.txt", file1);
        mailSender.send(mimeMessage);
    }
}