package com.gateways.emailgateway;

import com.gateways.plugins.PluginFactory;

import javax.mail.MessagingException;
import java.util.UUID;

public interface EmailVerification {
    PluginFactory PLUGIN_FACTORY = new PluginFactory();
    EmailVerification INSTANCE = (EmailVerification) PLUGIN_FACTORY.getPlugin(EmailVerification.class);
    void sendVerificationSignUpByEmail(String userEmail, UUID token) throws MessagingException;
    void sendVerificationForgetPasswordByEmail(String userEmail, UUID token) throws MessagingException;

}
