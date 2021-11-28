package com.main;

import com.businesslayer.UserAdministration;
import com.businesslayer.UserManagement;
import com.config.dbConfig;
import com.usermanagementlayer.UserManagementImpl;

public class Main {
    public static void main(String[] args) {
        UserAdministration userAdministration = new UserAdministration(new UserManagementImpl());
        //UserAdministration userAdministration = new UserAdministration(new UserManagementImpl());
        userAdministration.signUpUser("mike", "mike lopez", "chirca.mircea@gmail.com" );
        //dbConfig.getConnection();
    }
}
