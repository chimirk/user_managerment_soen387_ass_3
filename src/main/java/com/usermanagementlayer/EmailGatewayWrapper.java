package com.usermanagementlayer;

import com.gateways.emailgateway.EmailGateway;
import com.gateways.emailgateway.EmailGatewayInterface;

import javax.mail.MessagingException;
import java.util.UUID;

public class EmailGatewayWrapper {
    private EmailGatewayInterface emailGatewayInterface;

    public EmailGatewayWrapper(EmailGatewayInterface emailGatewayInterface) {
        this.emailGatewayInterface = emailGatewayInterface;
    }

    public void sendVerification(String email, UUID token, boolean isANewUser) throws MessagingException {
        emailGatewayInterface.sendVerification(email, token, isANewUser);
    }
}
