package com.usermanagementinterface;

public interface UserManagementInterface {
    void signUp( String username, String fullName, String email);
    void forgotPassword();
    boolean emailVerification();
    void changePassword();
}

