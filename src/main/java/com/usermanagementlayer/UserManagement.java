package com.usermanagementlayer;

import com.usermanagementinterface.UserManagementInterface;

import java.util.UUID;

public class UserManagement implements UserManagementInterface {

    @Override
    public void signUp(String username,String fullName,String email, String password) {
        UUID token = UUID.randomUUID();
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
}
