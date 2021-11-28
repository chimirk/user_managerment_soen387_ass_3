package com.businesslayer;


// this class will go into the business layer!!!
public class UserAdministration {
    private UserManagementInterface userManagementInterface;

    public UserAdministration(UserManagementInterface userManagementInterface) {
        this.userManagementInterface = userManagementInterface;
    }

    public void signUpUser(String username, String fullName, String email) {
        userManagementInterface.signUp(username, fullName, email);
    }

    public void forgotPasswordUser() {
        userManagementInterface.forgotPassword();
    }

    public void emailVerificationUser() {
        userManagementInterface.emailVerification();
    }
    public void changePasswordUser() {
        userManagementInterface.changePassword();
    }
}
