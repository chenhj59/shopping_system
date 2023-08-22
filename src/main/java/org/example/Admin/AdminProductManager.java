package org.example.Admin;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.example.Product.Product;
import org.example.Product.ProductQueryParams;

import java.util.Scanner;
import java.sql.*;

public class AdminProductManager {
    private static final String DB_URL = "jdbc:sqlite:products.db";

    public void listAllProducts(ArrayList<Product> products) {
        for(Product product : products){
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
    }

    public void addProduct(ArrayList<Product> products, Product product) {
        products.add(product);
    }

    public void updateProduct(ArrayList<Product> products, Product product) {
        int idx=0;
        for (Product pro : products) {
            if (pro.getName().equalsIgnoreCase(product.getName())) {
                products.set(idx, product);
                System.out.println("成功更新商品：" + product.getName());
                return;
            }
            idx++;
        }
        System.out.println("未找到名称为" + product.getName() + "的商品！");
    }

    public boolean deleteProduct(ArrayList<Product> products, String name) {
        Scanner scanner = new Scanner(System.in);
        for(Product product : products){
            if(product.getName().equals(name)){                
                System.out.println("是否要删除商品，删除后将无法恢复，按y确定，按n取消：");
                String flag = scanner.nextLine();
                if(flag.equalsIgnoreCase("y")){
                    products.remove(product);
                }else if(flag.equalsIgnoreCase("n")){
                    System.out.println("已经删除" + product.getName() + "！");
                }else{
                    System.out.println("已经取消删除！");
                }
                return true;
            }
        }
        return false;
    }

    public boolean searchProduct(ArrayList<Product> products, ProductQueryParams queryParams) {
        List<Product> results = new ArrayList<Product>();

        results = products.stream()
        .filter(product -> queryParams.hasName() || product.getName().equals(queryParams.getName()))
        .filter(product -> queryParams.hasManu() || product.getManufacturer().equals(queryParams.getManu()))
        .filter(product -> queryParams.hasRetPriceLower() || product.getRetailPrice() >= queryParams.getRetPriceLower())
        .filter(product -> queryParams.hasRetPriceUpper() || product.getRetailPrice() <= queryParams.getRetPriceUpper())
                .collect(Collectors.toList());
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
}