package com.usermanagementlayerinterface;

public interface UserManagement {
    void signUp( String username, String fullName, String email);
    void forgotPassword(String username) throws Exception;
    boolean emailVerification(String token, String password, boolean isANEwUser);
    void changePassword(String username, String oldPassword, String newPassword);
}
