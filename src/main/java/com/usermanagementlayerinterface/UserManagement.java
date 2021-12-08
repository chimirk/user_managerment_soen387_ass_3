package com.usermanagementlayerinterface;

import com.databaseEG.helper.User;
import com.usermanagementlayer.UserManagementException;

public interface UserManagement {
    void signUp( String username, String fullName, String email) throws Exception;
    void forgotPassword(String email) throws Exception;
    boolean emailVerification(String token, String password, boolean isANEwUser) throws UserManagementException;
    void changePassword(String username, String oldPassword, String newPassword) throws Exception;
    User userLogin(String username, String password);
}
