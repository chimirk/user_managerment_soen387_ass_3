package com.gateways.plugins;

import com.config.emailConfig;
import com.gateways.emailgateway.EmailVerification;
import com.transformpattern.EmailMessage;
import com.transformpattern.Scope;
import com.transformpattern.URL;
import com.transformpattern.XMLSerializer;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.bind.JAXBException;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class EmailVerPlugin1 implements EmailVerification {
    @Override
    public void sendVerificationSignUpByEmail(String userEmail, UUID token) throws MessagingException {
        Session session = emailConfig.eConfig();
        Message msg = new MimeMessage(session);
        String url = "http://localhost:8080/soen_387_part_2_war_exploded/ActivateAccount?thisToken=" + token;

        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setGreeting("Hello User");
        emailMessage.setScope(new Scope("You are currently", "signing up."));
        emailMessage.setMessage("Click The link bellow to verify your email.");
        emailMessage.setUrl(new URL(url, "verify email"));

        String xmlData = "";
        try {
            xmlData = XMLSerializer.serialize(emailMessage);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        StreamSource streamSource = new StreamSource("src/main/java/resources/messageTransformer.xsl");
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer(streamSource);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        StringWriter stringWriter = new StringWriter();
        StreamResult streamResult = new StreamResult(stringWriter);
        try {
            Objects.requireNonNull(transformer).transform(new StreamSource(new StringReader(xmlData)), streamResult);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        String emailContent = streamResult.getWriter().toString();


        InternetAddress[] toAddresses = { new InternetAddress(userEmail) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject("User Email Verification");
        msg.setSentDate(new Date());
        msg.setContent(emailContent,"text/html;");

        // sends the e-mail
        Transport.send(msg);
    }

    @Override
    public void sendVerificationForgetPasswordByEmail(String userEmail, UUID token) throws MessagingException {
        Session session = emailConfig.eConfig();
        Message msg = new MimeMessage(session);
        String url = "http://localhost:8080/soen_387_part_2_war_exploded/ActivateAccount?thisToken=" + token;

        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setGreeting("Hello User");
        emailMessage.setScope(new Scope("You ", "forgot your password."));
        emailMessage.setMessage("Click The link bellow to verify your email.");
        emailMessage.setUrl(new URL(url, "verify email"));

        String xmlData = "";
        try {
            xmlData = XMLSerializer.serialize(emailMessage);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        StreamSource streamSource = new StreamSource("src/main/java/resources/messageTransformer.xsl");
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer(streamSource);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        StringWriter stringWriter = new StringWriter();
        StreamResult streamResult = new StreamResult(stringWriter);
        try {
            Objects.requireNonNull(transformer).transform(new StreamSource(new StringReader(xmlData)), streamResult);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        String emailContent = streamResult.getWriter().toString();


        InternetAddress[] toAddresses = { new InternetAddress(userEmail) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject("User Email Verification");
        msg.setSentDate(new Date());
        msg.setContent(emailContent,"text/html;");

        // sends the e-mail
        Transport.send(msg);
    }
}
