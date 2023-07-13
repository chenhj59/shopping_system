package org.example;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class AdminProductManager {
    private List<Product> products;
    private static final String DB_URL = "jdbc:sqlite:products.db";

    public void listAllProducts(String position) {
        try(Connection connection = DriverManager.getConnection(DB_URL)){
            String sql = "SELECT * FROM products WHERE " + position;
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while(resultSet.next()){
                System.out.print("商品名字：" + resultSet.getString(2) + " ");
                System.out.print("商品价格：" + resultSet.getDouble(3) + " ");
                System.out.print("商品库存：" + resultSet.getInt(4) + " ");
                System.out.print("已售数量：" + resultSet.getInt(5) + " ");
                System.out.println();
            }
        } catch(SQLException e){
            System.out.println("初始化数据库失败: " + e.getMessage());
        }
    }

    public void addProduct(Product product) {
        try{
            Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement prep = conn.prepareStatement("INSERT INTO products(productName, price, inventory, quantitySold) values (?, ?, ?, ?)");
            prep.setString(1, product.getName());
            prep.setDouble(2, product.getPrice());
            prep.setInt(3, product.getInventory());
            prep.setInt(4, product.getQuantitySold());
            
            prep.executeUpdate();
            System.out.println("添加商品成功！");
        }catch (SQLException e) {
            System.out.println("初始化数据库失败: " + e.getMessage());
        }
    }

    public void updateProduct(String name, double newPrice) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                product.setPrice(newPrice);
                System.out.println("成功更新商品：" + product.getName());
                return;
            }
        }
        System.out.println("未找到名称为" + name + "的商品！");
    }

    public boolean deleteProduct(String name) {
        try(Connection connection = DriverManager.getConnection(DB_URL)){
            PreparedStatement prep = connection.prepareStatement("DELETE FROM products WHERE productName = ?");
            prep.setString(1, name);
            prep.executeUpdate();

            System.out.println("删除商品" + name + "信息成功！");

            return true;
        } catch(SQLException e){
            System.out.println("未找到商品" + name + "的信息！");

            return false;
        }
    }

    public boolean searchProduct(String name) {
        ResultSet resultSet = null;
        try(Connection connection = DriverManager.getConnection(DB_URL)){
            PreparedStatement prep = connection.prepareStatement("SELECT * FROM products WHERE productName = ?");
            prep.setString(1, name);
            resultSet = prep.executeQuery();

            if(resultSet.next()){
                System.out.print("商品名字：" + resultSet.getString(2) + " ");
                System.out.print("商品价格：" + resultSet.getString(3) + " ");
                System.out.print("商品库存：" + resultSet.getString(4) + " ");
                System.out.print("已售数量：" + resultSet.getString(5) + " ");
                System.out.println();
            }
            return true;
        } catch(SQLException e){
            System.out.println("未找到商品" + name + "的信息！");

            return false;
        }
    }
}