package com.databaseUM;

import com.config.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


public class UserGateway {


    private static final String INSERT_USER_INFO_SQL ="INSERT INTO poll_app.users" +
            "(username, email, full_name) VALUES" + "(?,?,?);";

    private static final String UPDATE_USER_PASSWORD_SQL ="UPDATE poll_app.users SET password = ? WHERE username = ?;";
    private static final String UPDATE_USER_ACTIVATION_STATUS_SQL ="UPDATE poll_app.users SET isValidated = ? WHERE username = ?;";
    private static final String SELECT_ALL_EMAILS_SQL ="SELECT ? FROM poll_app.users;";
    private static final String SELECT_USERNAME_FROM_EMAIL_SQL ="SELECT username FROM poll_app.users WHERE email = ?;";


    public static void saveUser(String userName, String userEmail, String userFullName) {
        try(Connection connection = dbConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_INFO_SQL)) {
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userEmail);
            preparedStatement.setString(3, userFullName);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void savePassword(String userPassword, String userName) {
        try(Connection connection = dbConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_PASSWORD_SQL)) {
            preparedStatement.setString(1, userPassword);
            preparedStatement.setString(2, userName);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateActivationStatus(String newStatus, String userName) {
        try(Connection connection = dbConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_ACTIVATION_STATUS_SQL)) {
            preparedStatement.setString(1, newStatus);
            preparedStatement.setString(2, userName);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isValidEmail (String userEmail) {
        String columnName = "email";
        String temp = "";
        try(Connection connection = dbConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMAILS_SQL)) {
            preparedStatement.setString(1, columnName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    temp = resultSet.getString(columnName);
                    if (temp.equals(userEmail)) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getUsernameFromEmail (String email) {
        String username;
        try(Connection connection = dbConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERNAME_FROM_EMAIL_SQL)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    username = resultSet.getString("username");
                    if (username != null) {
                        return username;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }



}

