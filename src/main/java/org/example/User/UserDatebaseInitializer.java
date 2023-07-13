package org.example.User;
import java.sql.*;
import java.util.ArrayList;

import org.example.DatebaseInitializer;

public class UserDatebaseInitializer implements DatebaseInitializer{
	private static final String DB_URL = "jdbc:sqlite:users.db";

    @Override
    public void initializeDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            
            // 创建用户信息表
            String createUserAccountTableQuery = "CREATE TABLE IF NOT EXISTS usersAccount (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, name TEXT, age TEXT, sex TEXT)";
            statement.executeUpdate(createUserAccountTableQuery);

            // 创建用户购物车表
            String createUserShopCartTableQuery = "CREATE TABLE IF NOT EXISTS usersShoppingCart (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, productName TEXT, productPrice double, productInventory int, productQuantitySold int, purchaseQuantity int)";
            statement.executeUpdate(createUserShopCartTableQuery);

            // 创建用户购物记录表
            String createUserPurchaseRecodeTableQuery = "CREATE TABLE IF NOT EXISTS usersPurchaseRecode (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, productName TEXT, productPrice double, purchaseQuantity int, time TEXT)";
            statement.executeUpdate(createUserPurchaseRecodeTableQuery);
            System.out.println("Database initialized successfully!");
        } catch (SQLException e) {
            System.out.println("初始化数据库失败: " + e.getMessage());
        } catch(ClassNotFoundException e){
            System.out.println("加载JDBC失败: " + e.getMessage());
        }
    }

    public void readUsers(ArrayList<User> users)
    {
        System.out.print(users);
    }

    public void writeUsers(ArrayList<User> users){
        System.out.print("a");
    }
}
