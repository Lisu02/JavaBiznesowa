package com.example.projektJava.service;

import org.aspectj.lang.annotation.AfterReturning;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class EmailService {

    private JavaMailSender mailSender;

    private final Logger superLogger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public EmailService(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    @Async
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("serwerOgloszen@example.com"); // Adres nadawcy
        mailSender.send(message);
        superLogger.info("Mail został wysłany prawidłowo -> " + message);
    }


    public String testowySerwisLogowanie(){
        superLogger.info("Testowy Log");
        return "Wynik Testowej Metody";
    }
}
