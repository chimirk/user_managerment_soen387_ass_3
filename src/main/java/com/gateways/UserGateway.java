/*package com.gateways;

import com.config.dbConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;*/


/*

public class UserGateway {
    private static final String INSERT_TOKEN_SQL ="INSERT INTO poll_app.verification_tokens" +
            "(verification_token, username) VALUES" + "(?,?);";

    private static final String INSERT_USER_INFO_SQL ="INSERT INTO poll_app.users" +
            "(username, email, full_name) VALUES" + "(?,?,?);";

    private static final String INSERT_USER_PASSWORD_SQL ="INSERT INTO poll_app.users" +
            "(username, password) VALUES" + "(?, ?);";

    public static void saveToken(UUID token, String userName) {
        try(Connection connection = dbConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TOKEN_SQL)) {
            preparedStatement.setString(1, token.toString());
            preparedStatement.setString(2, userName.toString());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
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
    public static void savePassword(String userPassword) {
        try(Connection connection = dbConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_PASSWORD_SQL)) {
            preparedStatement.setString(1, userPassword);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/
