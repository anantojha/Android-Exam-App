package com.comp2601.Assignment1;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {
    String username;
    String password;
    String emailSubject;
    String emailBody;
    Session session;

    public EmailService(String user, String pass, String subject, String body){
        username = user;
        password = pass;
        emailSubject = subject;
        emailBody = body;

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", Config.SMTP_DOMAIN);
        properties.put("mail.smtp.port", Config.SMTP_PORT);
        session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    public void sendEmail(String studentEmail){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(studentEmail));
            message.setSubject(emailSubject);
            message.setText(emailBody);
            Transport.send(message);
        } catch (MessagingException exception){
            throw new RuntimeException(exception);
        }
    }
}
