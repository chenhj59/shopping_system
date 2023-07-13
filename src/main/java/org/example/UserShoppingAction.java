package org.example;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
public class UserShoppingAction {
    private static final String DB_URL = "jdbc:sqlite:users.db";
    private List<Product> products = new ArrayList<Product>();
    private double sum = 0.0;
    private int quantity = 0;

    public Product searchProduct(String name) {
        ResultSet resultSet = null;
        Product product = new Product();
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:products.db")){
            PreparedStatement prep = connection.prepareStatement("SELECT * FROM products WHERE productName = ?");
            prep.setString(1, name);
            resultSet = prep.executeQuery();

            product.setName(resultSet.getString(2));
            product.setPrice(resultSet.getDouble(3));
            product.setInventory(resultSet.getInt(4));
            product.setQunatitySold(resultSet.getInt(5));

            return product;
        } catch(SQLException e){
            System.out.println(e.getMessage());

            return product;
        }
    }

    public void addItem(String username, Product product) {
        product = searchProduct(product.getName());
        try{
            Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement prep = conn.prepareStatement("INSERT INTO usersShoppingCart (username, productName, productPrice, productInventory, productQuantitySold, purchaseQuantity) values (?, ?, ?, ?, ?, ?)");
            prep.setString(1, username);
            prep.setString(2, product.getName());
            prep.setDouble(3, product.getPrice());
            prep.setInt(4, product.getInventory());
            prep.setInt(5, product.getQuantitySold());
            prep.setInt(6, product.getQuantity());
            prep.executeUpdate();
            System.out.println("添加商品成功！");
        }catch (SQLException e) {
            System.out.println("初始化数据库失败: " + e.getMessage());
        }
    }

    public boolean removeItem(Product product) {
        try(Connection connection = DriverManager.getConnection(DB_URL)){
            PreparedStatement prep = connection.prepareStatement("DELETE FROM usersShoppingCart WHERE productName = ?");
            prep.setString(1, product.getName());
            prep.executeUpdate();

            System.out.println("已经将商品" + product.getName() + "从购物车移除！");

            return true;
        } catch(SQLException e){
            System.out.println("未找到商品" + product.getName() + "的信息！");

            return false;
        }
    }

    public void updateQuantity(Product product) {
    }

    public void calculateTotalPrice(String username) {
        ResultSet resultSet = null;
        try(Connection connection = DriverManager.getConnection(DB_URL)){
            PreparedStatement prep = connection.prepareStatement("SELECT * FROM usersShoppingCart WHERE username = ?");
            prep.setString(1, username);
            resultSet = prep.executeQuery();

            while(resultSet.next()){
                Product product = new Product();
                product.setName(resultSet.getString(2));
                product.setPrice(resultSet.getDouble(3));
                product.setInventory(resultSet.getInt(4));
                product.setQunatitySold(resultSet.getInt(5));
                product.setQunatity(resultSet.getInt(6));
                products.add(product);
                sum += resultSet.getDouble(3);
            }
        } catch(SQLException e){
            System.out.println("结算出错：" + e.getMessage());
        }
    }

    public void checkout(String username) {
        calculateTotalPrice(username);
        addShoppingHistory();
        System.out.println("共需支付" + sum + "元");
    }

    public void addShoppingHistory() {
        try{
            Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement prep = conn.prepareStatement("INSERT INTO usersPurchaseRecode(username, productName, productPrice, purchaseQuantity, time) values (?, ?, ?, ?, ?)");
            for(var product : products){
                prep.setString(1, product.getName());
                prep.setDouble(2, product.getPrice());
                prep.setInt(3, product.getInventory());
                prep.setInt(4, product.getQuantitySold());
                prep.setInt(5, product.getQuantity());
                prep.executeUpdate();
            }
        }catch (SQLException e) {
            System.out.println("初始化数据库失败: " + e.getMessage());
        }
    }

    public void viewShoppingHistory(String username) {
        ResultSet resultSet = null;
        try(Connection connection = DriverManager.getConnection(DB_URL)){
            PreparedStatement prep = connection.prepareStatement("SELECT * FROM usersPurchaseRecode WHERE username = ?");
            prep.setString(1, username);
            resultSet = prep.executeQuery();

            while(resultSet.next()){
                System.out.print("商品名字：" + resultSet.getString(3) + " ");
                System.out.print("商品价格：" + resultSet.getString(4) + " ");
                System.out.print("购买数量：" + resultSet.getString(5) + " ");
                System.out.print("购买时间：" + resultSet.getString(6) + " ");
                System.out.println();
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
