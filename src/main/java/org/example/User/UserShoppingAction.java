package org.example.User;
import org.example.CartItem;
import org.example.PurchaseItem;

import java.util.stream.Collectors;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import java.text.SimpleDateFormat;

import org.example.Product.Product;
import org.example.Product.ProductQueryParams;

import java.sql.*;
public class UserShoppingAction {
    private static final String DB_URL = "jdbc:sqlite:users.db";
    private List<Product> products = new ArrayList<Product>();
    private double sum = 0.0;
    private int quantity = 0;

    public Boolean searchProduct(ArrayList<Product> products, int ID,  int quantity) {
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
            if(cartItem.getName().equals(product.getName())){                
                System.out.println("是否要删除用户信息，删除后将无法恢复，按y确定，按n取消：");
                String flag = scanner.nextLine();
                if(flag.equalsIgnoreCase("y")){
                    cartItems.remove(cartItem);
                }else if(flag.equalsIgnoreCase("n")){
                    System.out.println("已经删除" + cartItem.getName() + "！");
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

    public void calculateTotalPrice(ArrayList<CartItem> cartItems, ArrayList<CartItem> results, int userID) {
        for(CartItem cartItem : cartItems){
            if(cartItem.getID() == userID){
                sum += cartItem.getPurcPrice() * cartItem.getQuantity();
            }
        }
    }

    public void checkout(ArrayList<PurchaseItem> PurchaseItems, ArrayList<CartItem> cartItems, int userID) {
        ArrayList<CartItem> results = null;

        calculateTotalPrice(cartItems, results, userID);
        System.out.println("共需支付" + sum + "元！");
        System.out.println("已经通过微信支付" + sum + "元！");       
        addShoppingHistory(PurchaseItems, results);
    }

    public void addShoppingHistory(ArrayList<PurchaseItem> PurchaseItems, ArrayList<CartItem> results) {
        Date currentDate = new Date();

        // 创建 SimpleDateFormat 对象，并定义日期时间格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 使用 format() 方法将 Date 对象转换为字符串
        String dateString = dateFormat.format(currentDate);
        for(CartItem cartItem : results){
            PurchaseItems.add(new PurchaseItem(cartItem.getID(), cartItem.getUserID(), cartItem.getName(), cartItem.getPurcPrice(), cartItem.getQuantity(), dateString));
        }


    }

    public void viewShoppingHistory(ArrayList<PurchaseItem> PurchaseItems) {
        for(PurchaseItem PurchaseItem : PurchaseItems){
            System.out.print("商品编号：" + PurchaseItem.getID() + " ");
            System.out.print("商品名称：" + PurchaseItem.getName() + " ");
            System.out.print("商品价格：" + PurchaseItem.getPurcPrice() + " ");
            System.out.print("购买数量：" + PurchaseItem.getQuantity() + " ");
            System.out.print("购买时间：" + PurchaseItem.getTime() + " ");
            System.out.println();
        }
    }
}
