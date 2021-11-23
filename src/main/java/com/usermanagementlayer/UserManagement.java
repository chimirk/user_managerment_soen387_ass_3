package com.usermanagementlayer;

import com.usermanagementinterface.UserManagementInterface;

import java.util.UUID;
import com.gateways.*;

import javax.mail.MessagingException;

public class UserManagement implements UserManagementInterface {

    @Override
    public void signUp(String username,String fullName,String email) {
        UUID token = UUID.randomUUID();
        try {
            emailGateway.sendEmailVerification(email,token);
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
        userManagement.signUp("isratnoor", "israt", "kaziisratnoor@hotmail.com");
    }
}
