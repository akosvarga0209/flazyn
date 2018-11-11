package com.flazyn.service;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class EmailService {
    @Value("${website.url}")
    private  String WEBSITE_URL;
    private final Log log = LogFactory.getLog(this.getClass());

    private JavaMailSender javaMailSender;
    private TemplateEngine templateEngine;
    @Value("${spring.mail.username}")
    private  String MESSAGE_FROM;

    @Autowired
    public EmailService(TemplateEngine templateEngine){
        this.templateEngine = templateEngine;
    }

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMessage(String email, String activaion) {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(email);
            helper.setSubject("Flazyn registration");
            helper.setText(generateMailHtml(activaion), true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            log.error("Error while sending the email for the following address: "+email + " "+e);
        }

    }

    public String generateMailHtml(String text)
    {
        Map<String, Object> variables = new HashMap<>();
        variables.put("activationUrl", text);

        final String templateFileName = "en_activation_mail";
        String output = this.templateEngine.process(templateFileName, new Context(Locale.getDefault(), variables));

        return output;
    }




}