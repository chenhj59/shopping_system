package org.example.Product;
import java.sql.*;
import java.util.ArrayList;

import org.example.DatebaseInitializer;


import java.io.*;
public class ProductDatebaseInitializer implements DatebaseInitializer{
    @Override
    public void initializeDatabase() {
        File file = new File("productData.txt");
        
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

    public void read(ArrayList<Product> products) {
        try (BufferedReader br = new BufferedReader(new FileReader("productData.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] info = line.split(",");
                int productID = Integer.parseInt(info[0]);
                String productName = info[1];
                String productManufacturer = info[2];
                String productDateOfManufacture = info[3];
                String productModel = info[4];
                double productPurchasePrice = Double.parseDouble(info[5]);
                double productRetailPrice = Double.parseDouble(info[6]);
                int productInventory = Integer.parseInt(info[7]);
                products.add(new Product(productID, productName, productManufacturer, productDateOfManufacture, productModel, productPurchasePrice, productRetailPrice, productInventory));
            }
        } catch (IOException e) {
            System.out.println("读取文件失败: " + e.getMessage());
        }
    }

    public void write(ArrayList<Product> products) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("productData.txt"))) {
            for (Product product : products) {
                String line = product.getID() + "," + product.getName() + "," + product.getManufacturer() + "," + product.getDateOfManufacture()
                        + "," + product.getModel() + "," + product.getPurcPrice() + "," + product.getRetailPrice() + "," + product.getInventory();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
