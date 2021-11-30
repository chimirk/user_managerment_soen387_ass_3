package com.usermanagementlayer;

/*
import com.businesslayer.UserManagement;
*/

import java.util.UUID;

import com.databaseUM.ForgotPasswordTokensGateway;
import com.databaseUM.UserGateway;
import com.databaseUM.VerificationTokensGateway;
import com.gateways.emailgateway.emailGateway;
import com.usermanagementlayerinterface.UserManagement;

import javax.mail.MessagingException;

public class UserManagementImpl implements UserManagement {

    @Override
    public void signUp(String username,String fullName,String email) {
        UUID verificationToken = UUID.randomUUID();

        //save the token to the sql database
        VerificationTokensGateway.saveToken(verificationToken.toString(), username);

        //save user info
        UserGateway.saveUser(username, email, fullName);

        //send verification email to user
        try {
            emailGateway.sendVerification(email,verificationToken);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void forgotPassword(String email) throws Exception {

        /*The user enters his email. we verify the email if it is registered in the system and it is active, then we send an email with the forgot password token (link) and
        diactivate the account.
                When the user clicks on the link he/she is redirected to the new password form. He enters a new password and submits it. The system verifies if it is a different password
        than the old one. if it is ok, then we save the new password in the database table and reactivates the user's account.

        hint: use the emailVerification method to update the password .*/

        UUID forgotPasswordToken = UUID.randomUUID();
        String username = null;

        //verify email
        if (UserGateway.isValidEmail(email)) {
            username = UserGateway.getUsernameFromEmail(email);
        } else {
            throw new Exception("the provided email is not registered in the system.");
        }

        //save the token to the sql database
        ForgotPasswordTokensGateway.saveToken(forgotPasswordToken.toString(), username);

        //send verification email to user
        try {
            emailGateway.sendVerification(email,forgotPasswordToken);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean emailVerification(String token, String password, boolean isANewUser) {
        //Use the token to get the user and then update the password field of that user
        //Once the database has been updated, delete the token

        //get the username from token:
        String username;
        if (isANewUser) {
            username = VerificationTokensGateway.getUsernameFromToken(token);
        } else {
            username = ForgotPasswordTokensGateway.getUsernameFromToken(token);
        }

        if (password != null && username != null) {
            UserGateway.savePassword(password, username);
            UserGateway.updateActivationStatus("Y", username);

            //delete token
            if (isANewUser) {
                VerificationTokensGateway.deleteToken(token);
            } else {
                ForgotPasswordTokensGateway.deleteToken(token);
            }

            return true;
        }

        return false;
    }
    @Override
    public void changePassword(String username, String oldPassword, String newPassword) {
        /*
        Precondition: the user must be logged in.

        the user navigates to his profile and requests to change password. He is then redirect to the change password page. He enters the old password and the new password. he
        submits it and then, the system will verify if the old password is the right passord of the current user and if the new and the old are different passwords. once it has been
        verified, the system will update database with the new password of the user.
        */

    }

}