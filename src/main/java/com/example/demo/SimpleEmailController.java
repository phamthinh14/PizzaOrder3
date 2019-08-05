package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class SimpleEmailController {

    private TemplateEngine templateEngine;

    @Autowired
    Environment env;

    @Autowired
    public SimpleEmailController(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    private Properties GetProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
        props.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
        props.put("mail.smtp.host", env.getProperty("mail.smtp.host"));
        props.put("mail.smtp.port", env.getProperty("mail.smtp.port"));
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        return props;

    }

    private Session GetSession() {

        //Email Account Credentials ( it will be supervisor's credentials )
        final String username = "noreplythinh@gmail.com";
        final String password = "Conmeo123!@";

        //create session with username and passsword

        Session session = Session.getInstance(GetProperties(), new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        return session;
    }


    public String BuildTemplateWithContent(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("mailtemplate", context);
    }

    public void SendSimpleEmail( String content) {
        try {
            Message message = new MimeMessage(GetSession());

            //email address you're sending from
            message.setFrom(new InternetAddress("noreplythinh@gmail.com"));

            //email address you're sending email to ( to user email )
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("tpham130@montgomerycollege.edu"));

            //email  subject
            message.setSubject("Hello My Friend");

            //email content
            //if you want to set your text here and you don't care for user to write it in
            message.setText(content);


            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

//    @RequestMapping("/sendingordertomail")
//    public String mailPage() {
//        String content = "";
//        for (PizzaOrder pizzaOrder : orderRepository.findAllByUser(userService.getUser())) {
//            content += pizzaOrder.toString();
//        }
//
//        try {
//            sendEmail(userService.getUser().getEmail(), content);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "mailTemplate";
//    }

