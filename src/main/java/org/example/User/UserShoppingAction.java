package org.example.User;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import java.text.SimpleDateFormat;

import org.example.Product.Product;

public class UserShoppingAction {
    private double sum = 0.0;

    public Product searchProduct(ArrayList<Product> products, Product product) {
        for(Product pro : products){
            if(pro.getID() == product.getID()){
                return pro;
            }
        }
        return null;
    }

    public void addItem(ArrayList<CartItem> cartItems, int ID, Product product, int quantity) {
        cartItems.add(new CartItem(product.getID(), ID, product.getName(), 
        product.getPurcPrice(), quantity));
    }

    public boolean removeItem(ArrayList<CartItem> cartItems, Product product) {
        Scanner scanner = new Scanner(System.in);
        for(CartItem cartItem : cartItems){
            if(cartItem.getName().equals(product.getName())){                
                System.out.println("是否要从购物车移除商品，删除后将无法恢复，按y确定，按n取消：");
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

    public void updateQuantity(ArrayList<CartItem> cartItems, Product product, int quantity) {
        int idx = 0;
        if(quantity <= 0){
            removeItem(cartItems, product);
            return;
        }
        for(CartItem cartItem : cartItems){
            if(cartItem.getName().equals(product.getName())){
                cartItem.setQunatity(quantity);
                cartItems.set(idx, cartItem);         
                System.out.println("成功更新商品" + product.getName() + "的数量");
                return;
            }
            idx++;
        }
    }

    public ArrayList<CartItem> calculateTotalPrice(ArrayList<CartItem> cartItems, int userID) {
        ArrayList<CartItem> results = new ArrayList<CartItem>();
        for(CartItem cartItem : cartItems){
            if(cartItem.getUserID() == userID){
                sum += cartItem.getPurcPrice() * cartItem.getQuantity();
                results.add(cartItem);
            }
        }

        return results;
    }

    public void checkout(ArrayList<PurchaseItem> PurchaseItems, ArrayList<CartItem> cartItems, int userID) {
        ArrayList<CartItem> results = null;

        results = calculateTotalPrice(cartItems, userID);
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

    public void viewShoppingHistory(ArrayList<PurchaseItem> PurchaseItems, int userID) {
        for(PurchaseItem PurchaseItem : PurchaseItems){
            if(PurchaseItem.getUserID() == userID){
                System.out.print("商品编号：" + PurchaseItem.getID() + " ");
                System.out.print("商品名称：" + PurchaseItem.getName() + " ");
                System.out.print("商品价格：" + PurchaseItem.getPurcPrice() + " ");
                System.out.print("购买数量：" + PurchaseItem.getQuantity() + " ");
                System.out.print("购买时间：" + PurchaseItem.getTime() + " ");
                System.out.println();
            }
            
        }
    }
}
