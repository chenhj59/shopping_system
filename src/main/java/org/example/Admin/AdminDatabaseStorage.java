package org.example.Admin;
import org.example.DataStorage;

import java.sql.*;

import java.util.ArrayList;

public class AdminDatabaseStorage implements DataStorage<Admin>{
    private static final String DB_URL = "jdbc:sqlite:admins.db";

    public void initializeDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            String createTableQuery = "CREATE TABLE IF NOT EXISTS admins" + 
            "(adminname TEXT, " +
            "adminPassword TEXT)";
            statement.executeUpdate(createTableQuery);
            System.out.println("Database initialized successfully!");
        } catch (SQLException e) {
            System.out.println("初始化数据库失败: " + e.getMessage());
        } catch(ClassNotFoundException e){
            System.out.println("加载JDBC失败: " + e.getMessage());
        }
    }

    public void read(ArrayList<Admin> admins){
        try{
            Connection connection = DriverManager.getConnection(DB_URL);
            String sql = "SELECT * FROM admins WHERE " + "1";
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while(resultSet.next()){
                admins.add(new Admin(resultSet.getString("adminname"), resultSet.getString("adminPassword")));
            }
        } catch(SQLException e){
            System.out.println("初始化数据库失败: " + e.getMessage());
        }
    }

    public void write(ArrayList<Admin> admins){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // 1. 连接数据库
            connection = DriverManager.getConnection(DB_URL);

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM admins");

            // 2. 创建 PreparedStatement 对象，并指定 SQL 语句
            String sql = "INSERT INTO admins (adminname, adminPassword) VALUES (?, ?)";
            statement = connection.prepareStatement(sql);

            // 3. 执行批量添加
            for (Admin admin : admins) {
                statement.setString(1, admin.getName());
                statement.setString(2, admin.getPassword());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭连接和 PreparedStatement
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
