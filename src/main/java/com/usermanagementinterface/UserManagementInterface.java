package com.usermanagementinterface;

public interface UserManagementInterface {
    void signUp( String username, String fullName, String email, String password);
    void forgotPassword();
    boolean emailVerification();
    void changePassword();
}

