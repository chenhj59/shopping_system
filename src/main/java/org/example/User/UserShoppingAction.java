package org.example.User;
import org.example.CartItem;

import java.util.stream.Collectors;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import org.example.CartItem;
import org.example.Product.Product;
import org.example.Product.ProductQueryParams;

import java.sql.*;
public class UserShoppingAction {
    private static final String DB_URL = "jdbc:sqlite:users.db";
    private List<Product> products = new ArrayList<Product>();
    private double sum = 0.0;
    private int quantity = 0;

    public Product searchProduct(ArrayList<Product> products, int ID,  int quantity) {
        List<Product> results = new ArrayList<Product>();

        
        for(Product product : results){
            System.out.print("商品编号：" + product.getID() + " ");
                System.out.print("商品名称：" + product.getName() + " ");
                System.out.print("生产厂家：" + product.getManufacturer() + " ");
                System.out.print("生产日期：" + product.getDateOfManufacture() + " ");
                System.out.print("型号：" + product.getModel() + " ");
                System.out.print("进货价格：" + product.getPurcPrice() + " ");
                System.out.print("零售价格：" + product.getRetailPrice() + " ");
                System.out.print("数量：" + product.getInventory() + " ");
                System.out.println();
        }
        return true;
    }

    public void addItem(ArrayList<CartItem> cartItems, int ID, Product product, int quantity) {
        cartItems.add(new CartItem(product.getID(), ID, product.getName(), 
        product.getPurcPrice(), quantity));
    }

    public boolean removeItem(ArrayList<CartItem> cartItems, Product product) {
        Scanner scanner = new Scanner(System.in);
        for(CartItem cartItem : cartItems){
            if(cartItem.getUsername().equals(name)){                
                System.out.println("是否要删除用户信息，删除后将无法恢复，按y确定，按n取消：");
                String flag = scanner.nextLine();
                if(flag.equalsIgnoreCase("y")){
                    cartItems.remove(user);
                }else if(flag.equalsIgnoreCase("n")){
                    System.out.println("已经删除" + user.getUsername() + "！");
                }else{
                    System.out.println("已经取消删除！");
                }
                return true;
            }
        }
        return false;
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
