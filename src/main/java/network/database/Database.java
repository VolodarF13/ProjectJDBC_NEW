package network.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final Database INSTANCE = new Database();

    private Connection connection;


    public Database() {
        try {
            String url = "jdbc:h2:~/ProjectJDBC_NE/MegaSoft.mv.db";
            String user = "admin";
            String password = "admin";

            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println("Unable to establish connection to database." + e.getMessage());
        }
    }

    public int executeUpdate(String query) {
        try (Statement statement = connection.createStatement()) {
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println("Unable to execute statement to database " + e.getMessage());
            return -1;
        }
    }

    public static Database getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }

    public void close(){
        try{
            connection.close();
        } catch (SQLException e) {
            System.err.println("Unable to close connection to database " + e.getMessage());
        }
    }

}
