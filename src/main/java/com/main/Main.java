package com.main;

/*import com.businesslayer.UserAdministration;
import com.businesslayer.UserManagement;
import com.config.dbConfig;
import com.usermanagementlayer.UserManagementImpl;*/

import com.usermanagementlayer.UserManagementImpl;

public class Main {
    public static void main(String[] args) {
        UserManagementImpl userManagement = new UserManagementImpl();
        userManagement.signUp("mike", "mike lopez", "chirca.mircea@gmail.com");
    }
}
