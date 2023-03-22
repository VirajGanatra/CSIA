package com.example.ia_application.defaults;

import com.example.ia_application.Driver;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBClass {
    public static final String location = Objects.requireNonNull(Driver.class.getResource("events.db")).toExternalForm();
    public static final Connection connection = connect();

    public static Connection connect() {
        String dbPrefix = "jdbc:sqlite:";
        Connection connection;
        try {
            connection = DriverManager.getConnection(dbPrefix + location);
        } catch (SQLException exception) {
//            Logger.getAnonymousLogger().log(Level.SEVERE,
//                    LocalDateTime.now() + ": Could not connect to SQLite DB at " +
//                            location);
            exception.printStackTrace();
            return null;
        }
        return connection;
    }
}
