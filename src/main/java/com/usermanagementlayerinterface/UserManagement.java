package com.usermanagementlayerinterface;

import com.databaseEG.helper.User;
import com.usermanagementlayer.UserManagementException;

import java.security.NoSuchAlgorithmException;

public interface UserManagement {
    boolean signUp( String username, String fullName, String email) throws Exception;
    boolean forgotPassword(String email) throws Exception;
    boolean emailVerification(String token, String password, boolean isANEwUser) throws UserManagementException, NoSuchAlgorithmException;
    boolean changePassword(String username, String oldPassword, String newPassword) throws Exception;
    User userLogin(String username, String password);
}
