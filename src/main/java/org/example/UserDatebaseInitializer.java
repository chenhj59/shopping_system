package org.example;
import java.sql.*;

public class UserDatebaseInitializer {
	private static final String DB_URL = "jdbc:sqlite:usersAccount.db";

    public void initializeDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            String createTableQuery = "CREATE TABLE IF NOT EXISTS usersAccount (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, name TEXT, age TEXT, sex TEXT)";
            statement.executeUpdate(createTableQuery);
            System.out.println("Database initialized successfully!");
        } catch (SQLException e) {
            System.out.println("初始化数据库失败: " + e.getMessage());
        } catch(ClassNotFoundException e){
            System.out.println("加载JDBC失败: " + e.getMessage());
        }
    }
}
