package com.usermanagementlayer;

import com.businesslayer.UserManagement;

import java.util.UUID;

import com.databaseUM.UserGateway;
import com.databaseUM.VerificationTokensGateway;
import com.gateways.*;

import javax.mail.MessagingException;

public class UserManagementImpl implements UserManagement {

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
        UserManagementImpl userManagementImpl = new UserManagementImpl();
        //userManagement.signUp("isratnoor", "israt", "kaziisratnoor@hotmail.com");
        userManagementImpl.signUp("mike", "mike kirka", "chirca.mircea@gmail.com");
    }
}
