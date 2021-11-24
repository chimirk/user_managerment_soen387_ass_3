package com.gateways;
import com.config.emailConfig;

import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class emailGateway {

    public static void sendEmailVerification(String userEmail, UUID token) throws MessagingException {
        Session session = emailConfig.eConfig();
        Message msg = new MimeMessage(session);
        String url = "http://localhost:8080/soen_387_part_2_war_exploded/ActivateAccount?thisToken=" + token;
        String textLink = "Email Verification link";
        String content = "<a href='" + url + "'>" + textLink;
        String message = "Hello User click this link to verify your email ";

        InternetAddress[] toAddresses = { new InternetAddress(userEmail) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject("Verify User Email");
        msg.setSentDate(new Date());
        msg.setContent(message + " " + content,"text/html;");

        // sends the e-mail
        Transport.send(msg);
    }

}
