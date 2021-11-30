package com.gateways.emailgateway;

import com.gateways.plugins.PluginFactory;

import javax.mail.MessagingException;
import java.util.UUID;

public interface EmailVerification {
    EmailVerification INSTANCE = (EmailVerification) PluginFactory.getPlugin(EmailVerification.class);
    void sendVerificationSignUpByEmail(String userEmail, UUID token) throws MessagingException;
    void sendVerificationForgetPasswordByEmail(String userEmail, UUID token) throws MessagingException;

}
