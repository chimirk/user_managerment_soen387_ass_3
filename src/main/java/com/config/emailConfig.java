package com.config;

import java.util.Properties;
import javax.mail.Authenticator;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;


public class emailConfig {


    public static Session eConfig(){
        Properties prop = new Properties();

        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");

        String userName = "pollSystem@gmail.com";
        String password = "password";


        return Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });
    }

}
