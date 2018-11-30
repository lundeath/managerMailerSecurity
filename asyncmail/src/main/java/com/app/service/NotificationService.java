package com.app.service;

import com.app.domain.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private JavaMailSender javaMailSender;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async("asyncExecutor")
    public void sendNotification(String email, Mail mailPattern) throws MailException, InterruptedException {
        System.out.println("(" + Thread.currentThread().getName() + ")"
                + ": Sending email(\"" + mailPattern.getSubject() + "\") to "
                + email + "...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom(mailPattern.getFrom());
        mail.setSubject(mailPattern.getSubject());
        mail.setText(mailPattern.getText());
        javaMailSender.send(mail);

        System.out.println("(" + Thread.currentThread().getName() + ")" + ": Email sent to " + email + "!");
    }

}