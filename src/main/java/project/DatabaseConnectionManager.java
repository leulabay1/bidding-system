package project;

import java.sql.*;

public class DatabaseConnectionManager {
    private static Connection connection;

    private DatabaseConnectionManager() {
        // private constructor to prevent instantiation
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            // Initialize the connection if it is not available or closed
            initializeConnection();
        }
        return connection;
    }

    private static void initializeConnection() throws SQLException {
        // Load the JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//          System.out.println("-------------------class found");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handle exceptions appropriately
            throw new SQLException("JDBC driver not found.");
        }

        // Database connection properties
        String url = "jdbc:mysql://localhost:3306/";
        String username = "root";
        String password = "";
        String databaseName = "biddingSystemDB";

        // Establish a connection
        try (Connection tempConnection = DriverManager.getConnection(url, username, password)) {
//            System.out.println("-------------------connected to the database");

            // Check if the database exists
            if (!databaseExists(tempConnection, databaseName)) {
                // If the database doesn't exist, create it
                createDatabase(tempConnection, databaseName);
            }

            // Connect to the specified database
            url = "jdbc:mysql://localhost:3306/" + databaseName;
            connection = DriverManager.getConnection(url, username, password);

            createClientTable();
            createUserTable();
            createBidTable();
            createItemTable();


        } catch (SQLException e) {
            // System.out.println("-------------------couldn't create the database");
            e.printStackTrace(); // Handle exceptions appropriately
            throw new SQLException("Failed to initialize the database connection.");
        }
    }

    private static void createBidTable() throws SQLException {
        String createAuctionTableSQL = "CREATE TABLE IF NOT EXISTS bids (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "date DATE," +
                "amount INT NOT NULL," +
                "bidder_id INT NOT NULL," +
                "item_id INT NOT NULL," +
                "statement TEXT," +
                "winner BOOLEAN," +
                "paid_client BOOLEAN," +
                "paid_bidder BOOLEAN)";

        executeUpdate(createAuctionTableSQL);
    }

    private static void createClientTable() throws SQLException {
        String createUserTableSQL = "CREATE TABLE IF NOT EXISTS clients (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "password VARCHAR(255) NOT NULL," +
                "username VARCHAR(255) UNIQUE," +
                "address VARCHAR(255)," +
                "bank_num INT)";

        executeUpdate(createUserTableSQL);
    }

    private static void createItemTable() throws SQLException {
        String createAuctionTableSQL = "CREATE TABLE IF NOT EXISTS items (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "authorId INT," +
                "end_date DATE," +
                "description TEXT," +
                "title TEXT," +
                "closed BOOLEAN)";

        executeUpdate(createAuctionTableSQL);
    }

    private static void createUserTable() throws SQLException {
        String createCustomerTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "password VARCHAR(255) NOT NULL," +
                "username VARCHAR(255) NOT NULL," +
                "address VARCHAR(255) NOT NULL)";

        executeUpdate(createCustomerTableSQL);
    }


    private static void executeUpdate(String sql) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        }
    }

    private static boolean databaseExists(Connection connection, String databaseName) throws SQLException {
        String query = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, databaseName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private static void createDatabase(Connection connection, String databaseName) throws SQLException {
        String query = "CREATE DATABASE " + databaseName;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        }
    }
}
