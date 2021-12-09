package com.usermanagementlayer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.UUID;
import com.databaseEG.ForgotPasswordTokensGateway;
import com.databaseEG.UserGateway;
import com.databaseEG.VerificationTokensGateway;
import com.databaseEG.helper.User;
import com.gateways.emailgateway.EmailGateway;
import com.gateways.emailgateway.EmailGatewayInterface;
import com.usermanagementlayerinterface.UserManagement;

import javax.mail.MessagingException;
import javax.xml.bind.DatatypeConverter;

public class UserManagementImpl implements UserManagement {

    @Override
    public boolean signUp(String username, String fullName, String email) throws UserManagementException, MessagingException {
        UUID verificationToken = UUID.randomUUID();

        if (UserGateway.isANewUser(username)) {
            //save the token to the sql database
            VerificationTokensGateway.saveToken(verificationToken.toString(), username);
            //save user info
            UserGateway.saveUser(username, email, fullName);
            //send verification email to user
            EmailGatewayWrapper emailGatewayWrapper = new EmailGatewayWrapper(new EmailGateway());
            emailGatewayWrapper.sendVerification(email, verificationToken, true);
            return true;

        } else {
            throw new UserManagementException("This username is already registered");
        }
    }


    @Override
    public boolean forgotPassword(String email) throws UserManagementException, MessagingException {

        /*The user enters his email. we verify the email if it is registered in the system and it is active, then we send an email with the forgot password token (link) and
        deactivate the account.
                When the user clicks on the link he/she is redirected to the new password form. He enters a new password and submits it. The system verifies if it is a different password
        than the old one. if it is ok, then we save the new password in the database table and reactivates the user's account.

        hint: use the emailVerification method to update the password .*/

        UUID forgotPasswordToken = UUID.randomUUID();
        String username = null;

        //verify email
        if (UserGateway.isValidEmail(email)) {
            username = UserGateway.getUsernameFromEmail(email);
            //save the token to the sql database
            ForgotPasswordTokensGateway.saveToken(forgotPasswordToken.toString(), username);
            //deactivate account
            UserGateway.updateActivationStatus("N", username);
            //send verification email to user
            EmailGatewayWrapper emailGatewayWrapper = new EmailGatewayWrapper(new EmailGateway());
            emailGatewayWrapper.sendVerification(email, forgotPasswordToken, false);
            return true;
        } else {
            throw new UserManagementException("the provided email is not registered in the system.");
        }
    }

    @Override
    public boolean emailVerification(String token, String password, boolean isANewUser) throws UserManagementException, NoSuchAlgorithmException {
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
            String passwordHashed = "";
            MessageDigest md1 = MessageDigest.getInstance("MD5");
            md1.update(password.getBytes());
            byte[] digest1 = md1.digest();
            passwordHashed = DatatypeConverter
                    .printHexBinary(digest1).toUpperCase();

            UserGateway.savePassword(passwordHashed, username);
            UserGateway.updateActivationStatus("Y", username);

            //delete token
            if (isANewUser) {
                VerificationTokensGateway.deleteToken(token);
            } else {
                ForgotPasswordTokensGateway.deleteToken(token);
            }
            return true;
        } else {
            throw new UserManagementException("password or username is empty or null");
        }

    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) throws Exception {
        /*
        Precondition: the user must be logged in.

        the user navigates to his profile and requests to change password. He is then redirect to the change password page.
        He enters the old password and the new password. he
        submits it and then, the system will verify if the old password is the right passord of the current user
        and if the new and the old are different passwords. once it has been
        verified, the system will update database with the new password of the user.
        */

        String oldPasswordHashed = "";
        MessageDigest md1 = MessageDigest.getInstance("MD5");
        md1.update(oldPassword.getBytes());
        byte[] digest1 = md1.digest();
        oldPasswordHashed = DatatypeConverter
                .printHexBinary(digest1).toUpperCase();

        String newPasswordHashed = "";
        MessageDigest md2 = MessageDigest.getInstance("MD5");
        md2.update(newPassword.getBytes());
        byte[] digest2 = md2.digest();
        newPasswordHashed = DatatypeConverter
                .printHexBinary(digest2).toUpperCase();

        if (oldPassword.equals(newPassword)) {
            throw new UserManagementException("the old password and the new password are the same. please use a different new password");
        }

        //verify if there is a match for username and old password
        if (UserGateway.isUserNameAndOldPasswordValid(username, oldPasswordHashed)) {
            //update new password
            UserGateway.savePassword(newPasswordHashed, username);
            return true;
        } else {
            throw new UserManagementException("Provided combination of username and old password does not exist in database");
        }
    }

    @Override
    public User userLogin(String username, String password) {
        User result = new User();
        ArrayList<User> users = UserGateway.getAllUsersInfo();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)) {
                result.setUsername(users.get(i).getUsername());
                result.setPassword(users.get(i).getPassword());
                result.setEmail(users.get(i).getEmail());
                result.setFullName(users.get(i).getFullName());
                return result;
            }
        }
        return null;
    }
}
