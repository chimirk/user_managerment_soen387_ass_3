package com.usermanagementlayer;

import com.businesslayer.UserManagementInterface;

import java.util.UUID;

import com.databaseUM.UserGateway;
import com.databaseUM.VerificationTokensGateway;
import com.gateways.*;

import javax.mail.MessagingException;

public class UserManagement implements UserManagementInterface {

    @Override
    public void signUp(String username,String fullName,String email) {
        UUID token = UUID.randomUUID();

        //save the token to the sql database
        VerificationTokensGateway.saveToken(token, username);

        //save user info
        UserGateway.saveUser(username, email, fullName);

        //send verification email to user
        try {
            emailGateway.sendVerification(email,token);
        } catch (MessagingException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void forgotPassword() {

    }

    @Override
    public boolean emailVerification() {
        return false;
    }

    @Override
    public void changePassword() {

    }

    public static void main(String[] args) {
        UserManagement userManagement = new UserManagement();
        //userManagement.signUp("isratnoor", "israt", "kaziisratnoor@hotmail.com");
        userManagement.signUp("mike", "mike kirka", "chirca.mircea@gmail.com");
    }
}
