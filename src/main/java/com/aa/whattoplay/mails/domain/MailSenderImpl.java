package com.aa.whattoplay.mails.domain;

import com.aa.ddd.common.annotations.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@DomainService
public class MailSenderImpl{
    private final JavaMailSender javaMailSender;

    @Autowired
    public MailSenderImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getAddress());
        message.setSubject(email.getSubject());
        message.setText(email.getText() + " " + email.getSendDate());
        javaMailSender.send(message);
    }

}
