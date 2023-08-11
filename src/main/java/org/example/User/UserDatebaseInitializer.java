package org.example.User;
import java.sql.*;
import java.util.ArrayList;

import org.example.DatebaseInitializer;

import java.io.*;
public class UserDatebaseInitializer implements DatebaseInitializer{
    @Override
    public void initializeDatabase() {
        File file = new File("usersAccount.txt");
        
        try {
            if (!file.exists()) {
                boolean created = file.createNewFile();
                if (created) {
                    System.out.println("文件已创建: " + file.getName());
                } else {
                    System.out.println("无法创建文件。");
                }
            } else {
                System.out.println("文件已存在: " + file.getName());
            }
        } catch (IOException e) {
            System.out.println("发生IO异常: " + e.getMessage());
        }
        file = new File("usersShoppingCart.txt");
        
        try {
            if (!file.exists()) {
                boolean created = file.createNewFile();
                if (created) {
                    System.out.println("文件已创建: " + file.getName());
                } else {
                    System.out.println("无法创建文件。");
                }
            } else {
                System.out.println("文件已存在: " + file.getName());
            }
        } catch (IOException e) {
            System.out.println("发生IO异常: " + e.getMessage());
        }

        file = new File("usersPurchaseRecord.txt");
        
        try {
            if (!file.exists()) {
                boolean created = file.createNewFile();
                if (created) {
                    System.out.println("文件已创建: " + file.getName());
                } else {
                    System.out.println("无法创建文件。");
                }
            } else {
                System.out.println("文件已存在: " + file.getName());
            }
        } catch (IOException e) {
            System.out.println("发生IO异常: " + e.getMessage());
        }
    }

    public void read(ArrayList<User> users, ArrayList<CartItem> cartItems, ArrayList<PurchaseItem> purchaseItems) {
        try (BufferedReader br = new BufferedReader(new FileReader("usersAccount.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] info = line.split(",");
                users.add(new User(Integer.parseInt(info[0]), info[1], info[2], info[3], info[4], Double.parseDouble(info[5]), info[6], info[7]));
            }
        } catch (IOException e) {
            System.out.println("读取文件失败: " + e.getMessage());
        }

        try (BufferedReader br = new BufferedReader(new FileReader("usersShoppingCart.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] info = line.split(",");
                cartItems.add(new CartItem(Integer.parseInt(info[0]), Integer.parseInt(info[1]), info[2], Double.parseDouble(info[3]), Integer.parseInt(info[4])));
            }
        } catch (IOException e) {
            System.out.println("读取文件失败: " + e.getMessage());
        }

        try (BufferedReader br = new BufferedReader(new FileReader("usersPurchaseRecord.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] info = line.split(",");
                purchaseItems.add(new PurchaseItem(Integer.parseInt(info[0]), Integer.parseInt(info[1]), info[2], Double.parseDouble(info[3]), Integer.parseInt(info[4]), info[5]));
            }
        } catch (IOException e) {
            System.out.println("读取文件失败: " + e.getMessage());
        }
    }

    public void write(ArrayList<User> users, ArrayList<CartItem> cartItems, ArrayList<PurchaseItem> purchaseItems) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("usersAccount.txt"))) {
            for (User user : users) {
                String line = user.getID() + "," + user.getUsername() + "," + user.getPassword() + "," + user.getUserLevel() + "," + user.getRegisterTime() +
                        "," + user.getTotalCost() + "," + user.getPhoneNumber() + "," + user.getEmail();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("usersShoppingCart.txt"))) {
            for (CartItem cartItem : cartItems) {
                String line = cartItem.getID() + "," + cartItem.getUserID() + "," + cartItem.getName() + "," + cartItem.getPurcPrice() + "," + cartItem.getQuantity();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("usersPurchaseRecord.txt"))) {
            for (PurchaseItem purchaseItem : purchaseItems) {
                String line = purchaseItem.getID() + "," + purchaseItem.getUserID() + "," + purchaseItem.getName() + "," + purchaseItem.getPurcPrice() + ","
                        + purchaseItem.getQuantity() + "," + purchaseItem.getTime();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
