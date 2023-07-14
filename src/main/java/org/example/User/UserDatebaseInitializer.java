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
            String createUserAccountTableQuery = "CREATE TABLE IF NOT EXISTS usersAccount" + 
            "(userID INT usersAccount key NOT NULL," +
            "userName TEXT NOT NULL,"+
            "password TEXT NOT NULL,"+
            "userLevel TEXT,"+
            "registerTime TEXT, "+
            "totalCost double, "+
            "userPhoneNumber String, "+
            "userEmail String)";
            statement.executeUpdate(createUserAccountTableQuery);

            // 创建用户购物车表
            String createUserShopCartTableQuery = "CREATE TABLE IF NOT EXISTS usersShoppingCart" + 
            "(productID INT usersShoppingCart key NOT NULL," +
            "userID INT NOT NULL,"+
            "productName TEXT NOT NULL,"+
            "productRetailPrice double, "+
            "quantity int)";
            statement.executeUpdate(createUserShopCartTableQuery);

            // 创建用户购物记录表
            String createUserPurchaseRecodeTableQuery = "CREATE TABLE IF NOT EXISTS usersPurchaseRecord" + 
            "(productID INT usersPurchaseRecord key NOT NULL," +
            "userID INT NOT NULL,"+
            "productName TEXT NOT NULL,"+
            "productRetailPrice double, "+
            "quantity int,"+
            "time String)";
            statement.executeUpdate(createUserPurchaseRecodeTableQuery);
            System.out.println("Database initialized successfully!");
        } catch (SQLException e) {
            System.out.println("初始化数据库失败: " + e.getMessage());
        } catch(ClassNotFoundException e){
            System.out.println("加载JDBC失败: " + e.getMessage());
        }
    }

    public void read(ArrayList<User> users, ArrayList<CartItem> cartItems, ArrayList<PurchaseItem> purchaseItems)
    {
        try(Connection connection = DriverManager.getConnection(DB_URL)){
            String sql = "SELECT * FROM usersAccount WHERE " + "1";
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while(resultSet.next()){
                users.add(new User(resultSet.getInt("userID"), resultSet.getString("userName"),
                resultSet.getString("password"), resultSet.getString("userLevel"), resultSet.getString("registerTime"),
                resultSet.getDouble("totalCost"), resultSet.getString("userPhoneNumber"), resultSet.getString("userEmail")));
            }
            sql = "SELECT * FROM usersShoppingCart WHERE " + "1";
            resultSet = stmt.executeQuery(sql);
            while(resultSet.next()){
                cartItems.add(new CartItem(resultSet.getInt("productID"),resultSet.getInt("userID"), 
                resultSet.getString("productName"),resultSet.getDouble("productRetailPrice"), 
                resultSet.getInt("quantity")));
            }
            sql = "SELECT * FROM usersPurchaseRecord WHERE " + "1";
            resultSet = stmt.executeQuery(sql);
            while(resultSet.next()){
                purchaseItems.add(new PurchaseItem(resultSet.getInt("productID"),resultSet.getInt("userID"), 
                resultSet.getString("productName"),resultSet.getDouble("productRetailPrice"), 
                resultSet.getInt("quantity"), resultSet.getString("time")));
            }
        } catch(SQLException e){
            System.out.println("初始化数据库失败: " + e.getMessage());
        }
    }

    public void write(ArrayList<User> users, ArrayList<CartItem> cartItems, ArrayList<PurchaseItem> purchaseItems){
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // 1. 连接数据库
            connection = DriverManager.getConnection(DB_URL);

            // 对用户账号信息操作
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM usersAccount");

            // 2. 创建 PreparedStatement 对象，并指定 SQL 语句
            String sql = "INSERT INTO usersAccount (userID, userName, password, userLevel, registerTime , totalCost , userPhoneNumber , userEmail) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);

            // 3. 执行批量添加
            for (User user : users) {
                statement.setInt(1, user.getID());
                statement.setString(2, user.getUsername());
                statement.setString(3, user.getPassword());
                statement.setString(4, user.getUserLevel());
                statement.setString(5, user.getRegisterTime());
                statement.setDouble(6, user.getTotalCost());
                statement.setString(7, user.getPhoneNumber());
                statement.setString(8, user.getEmail());
                statement.addBatch();
            }
            statement.executeBatch();

            //对用户购物车信息操作
            stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM usersShoppingCart");

            sql = "INSERT INTO usersShoppingCart (productID, userID, productName, productRetailPrice, quantity) VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);

            for (CartItem cartItem : cartItems) {
                statement.setInt(1, cartItem.getID());
                statement.setInt(2, cartItem.getUserID());
                statement.setString(3, cartItem.getName());
                statement.setDouble(4, cartItem.getPurcPrice());
                statement.setInt(5, cartItem.getQuantity());
                statement.addBatch();
            }
            statement.executeBatch();

            // 对用户消费记录操作
            stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM usersPurchaseRecord");
            sql = "INSERT INTO usersPurchaseRecord (productID, userID, productName, productRetailPrice, quantity, time) VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);

            for (PurchaseItem purchaseItem : purchaseItems) {
                statement.setInt(1, purchaseItem.getID());
                statement.setInt(2, purchaseItem.getUserID());
                statement.setString(3, purchaseItem.getName());
                statement.setDouble(4, purchaseItem.getPurcPrice());
                statement.setInt(5, purchaseItem.getQuantity());
                statement.setString(6, purchaseItem.getTime());
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
