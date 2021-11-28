package com.gateways;

import javax.mail.MessagingException;
import java.util.UUID;

public interface EmailVerification {
    EmailVerification INSTANCE = (EmailVerification) PluginFactory.getPlugin(EmailVerification.class);
    void sendVerificationByEmail(String userEmail, UUID token) throws MessagingException;

}
