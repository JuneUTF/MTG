package com.juneutf.mtg.config.vender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.juneutf.mtg.model.TableModel;

/**
 * メール送信に関するサービスクラスです。
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private TemplateEngine templateEngine;
    
    /**
     * ユーザーにトークンを含むメールを送信します。
     *
     * @param email メールの宛先アドレス
     * @param token 送信するトークン
     * @throws MessagingException メッセージ送信中に発生した例外
     */
    public void sendToken(String email, String token) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(email);
        //件名設定
        helper.setSubject("【K＆Kソフト】パスワード再設定完了のお知らせ");
        Context context = new Context();
        //トークン渡す
        context.setVariable("token", token);
        //HTMLファイアウォール定義
        String htmlContent = templateEngine.process("email/token", context);
        helper.setText(htmlContent, true);
        emailSender.send(message);
    }
    /**
     * ユーザーにトークンを含むメールを送信します。
     *
     * @param email メールの宛先アドレス
     * @param token 送信するトークン
     * @throws MessagingException メッセージ送信中に発生した例外
     */
    public void sendRegister(TableModel tableModel) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(tableModel.getEmail());
        //件名設定
        helper.setSubject("【K＆Kソフト】会議室予約アカウント登録完了のお知らせ");
        Context context = new Context();
        //トークン渡す
        context.setVariable("fullname", tableModel.getFullname());
        context.setVariable("username", tableModel.getUsername());
        context.setVariable("password", tableModel.getPassword());
        //HTMLファイアウォール定義
        String htmlContent = templateEngine.process("email/register", context);
        helper.setText(htmlContent, true);
        emailSender.send(message);
    }
}
