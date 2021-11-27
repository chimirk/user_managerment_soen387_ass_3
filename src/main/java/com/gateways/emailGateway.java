package com.gateways;
import com.config.dbConfig;
import com.config.emailConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.sql.Timestamp;
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

    public static void sendVerification(String userEmail, UUID token) throws MessagingException {

        EmailVerification emailVerification = new EmailVerificationImpl();
        emailVerification.sendVerificationByEmail(userEmail, token);

    }
}
