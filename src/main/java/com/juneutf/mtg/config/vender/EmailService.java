package com.juneutf.mtg.config.vender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.juneutf.mtg.model.LoginModel;



@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private TemplateEngine templateEngine;
    public void sendToken(String email,String token) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");
        helper.setTo(email);
        helper.setSubject("【K＆Kソフト】パスワード再設定完了のお知らせ");
        Context context = new Context();
        context.setVariable("token", token);
        String htmlContent = templateEngine.process("email/token", context);
        helper.setText(htmlContent, true);
        emailSender.send(message);
    }
}
