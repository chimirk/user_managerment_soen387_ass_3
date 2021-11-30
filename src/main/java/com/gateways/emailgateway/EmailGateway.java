package com.gateways.emailgateway;

import javax.mail.MessagingException;
import java.util.Objects;
import java.util.UUID;

public class EmailGateway {

    public static void sendVerification(String userEmail, UUID token, boolean isANewUser) throws MessagingException {

        if (isANewUser) {
            Objects.requireNonNull(EmailVerification.INSTANCE).sendVerificationSignUpByEmail(userEmail, token);
        } else {
            Objects.requireNonNull(EmailVerification.INSTANCE).sendVerificationForgetPasswordByEmail(userEmail, token);
        }

    }
}
