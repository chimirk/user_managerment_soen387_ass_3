package com.gateways.emailgateway;

import javax.mail.MessagingException;
import java.util.UUID;

public interface EmailGatewayInterface {
    public void sendVerification(String userEmail, UUID token, boolean isANewUser) throws MessagingException;
}
