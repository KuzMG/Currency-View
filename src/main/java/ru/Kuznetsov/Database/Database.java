package ru.Kuznetsov.Database;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final Logger log = LoggerFactory.getLogger(Database.class);
    private static Database instance = new Database();
    private Connection connection;
    public static Database getInstance() {
        return instance;
    }
    public Connection getConnection() throws IOException, SQLException {
        if(connection==null) {
            prepareDirectory();
            initDb();
        }
        return connection;
    }
    private static final String pathDB = "jdbc:sqlite:src/main/java/ru/Kuznetsov/Database/currencyExchange.db";
    private static final String tableDB =  "CREATE TABLE IF NOT EXISTS currencyExchange (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "value REAL NOT NULL,\n" +
            "nominal INTEGER NOT NULL,\n" +
            "currency_name VARCHAR(100) NOT NULL,\n" +
            "currency_code VARCHAR(3) NOT NULL,\n" +
            "date VARCHAR(100) NOT NULL\n" +
            ");";

    public Database() {
    }

    private void prepareDirectory() throws IOException {
        try {
            connection = DriverManager.getConnection(pathDB);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void initDb(){
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(tableDB);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void closeConnection(){
        if(connection!=null){
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                log.error("error close connection");
                throw new RuntimeException(e);
            }
        }
    }
}

