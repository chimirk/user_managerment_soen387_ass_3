package com.gateways;

import javax.mail.MessagingException;
import java.util.UUID;

public class emailGateway {

    //private EmailVerification emailVerification;

    //public emailGateway(EmailVerification emailVerification) {
        //this.emailVerification = emailVerification;
    //}

    /*public static void sendVerification(String userEmail, UUID token, EmailVerification emailVerification) throws MessagingException {

        EmailVerification emailVerification = new EmailVerificationImpl();
        emailVerification.sendVerificationByEmail(userEmail, token);

    }*/

    public static void sendVerification(String userEmail, UUID token) throws MessagingException {

        EmailVerification emailVerification = new EmailVerificationImpl();
        emailVerification.sendVerificationByEmail(userEmail, token);

    }
}
