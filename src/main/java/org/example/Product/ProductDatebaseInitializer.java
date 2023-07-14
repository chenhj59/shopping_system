package org.example.Product;
import java.sql.*;
import java.util.ArrayList;

import org.example.DatebaseInitializer;
public class ProductDatebaseInitializer implements DatebaseInitializer{
    private static final String DB_URL = "jdbc:sqlite:products.db";

    public void initializeDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            String createTableQuery = "CREATE TABLE IF NOT EXISTS products" + 
            "(productID INT products key NOT NULL," +
            "productName TEXT NOT NULL,"+
            "productManufacturer TEXT NOT NULL,"+
            "productDateOfManufacture TEXT,"+
            "productModel TEXT, "+
            "productPurchasePrice double, "+
            "productRetailPrice double, "+
            "productInventory int)";
            statement.executeUpdate(createTableQuery);
            System.out.println("Database initialized successfully!");
        } catch (SQLException e) {
            System.out.println("初始化数据库失败: " + e.getMessage());
        } catch(ClassNotFoundException e){
            System.out.println("加载JDBC失败: " + e.getMessage());
        }
    }

    public void read(ArrayList<Product> products){
        try(Connection connection = DriverManager.getConnection(DB_URL)){
            String sql = "SELECT * FROM products WHERE " + "1";
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while(resultSet.next()){
                products.add(new Product(resultSet.getInt("productID"), resultSet.getString("productName"),
                resultSet.getString("productManufacturer"), resultSet.getString("productDateOfManufacture"), resultSet.getString("productDateOfManufacture"),
                resultSet.getDouble("productModel"), resultSet.getDouble("productPurchasePrice"), resultSet.getInt("productInventory")));
            }
        } catch(SQLException e){
            System.out.println("初始化数据库失败: " + e.getMessage());
        }
    }

    public void write(ArrayList<Product> products){
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // 1. 连接数据库
            connection = DriverManager.getConnection(DB_URL);

            // 2. 创建 PreparedStatement 对象，并指定 SQL 语句
            String sql = "INSERT INTO products (productID, productName, productManufacturer, productDateOfManufacture, productModel , productPurchasePrice , productRetailPrice , productInventory) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);

            // 3. 执行批量添加
            for (Product product : products) {
                statement.setInt(1, product.getID());
                statement.setString(2, product.getName());
                statement.setString(3, product.getManufacturer());
                statement.setString(4, product.getDateOfManufacture());
                statement.setString(5, product.getModel());
                statement.setDouble(6, product.getPurcPrice());
                statement.setDouble(7, product.getRetailPrice());
                statement.setInt(8, product.getInventory());
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
