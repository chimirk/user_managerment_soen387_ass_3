package com.main;

import com.businesslayer.UserAdministration;
import com.config.dbConfig;
import com.usermanagementlayer.UserManagement;

public class Main {
    public static void main(String[] args) {
        //UserAdministration userAdministration = new UserAdministration(new UserManagement());
        //userAdministration.signUpUser("mike", "mike lopez", "chirca.mircea@gmail.com" );
        dbConfig.getConnection();
    }
}
