package org.example;
import java.util.ArrayList;
import java.util.List;

public class AdminProductManager {
    private List<Product> products;

    public void listAllProducts() {
        if (products.isEmpty()) {
            System.out.println("没有商品信息！");
        } else {
            System.out.println("所有商品信息：");
            for (Product product : products) {
                System.out.println("名称：" + product.getName() + "，价格：" + product.getPrice());
            }
        }
    }

    public void addProduct(String name, double price) {
        Product product = new Product(name, price);
        products.add(product);
        System.out.println("成功添加商品：" + product.getName());
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

    public void deleteProduct(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                products.remove(product);
                System.out.println("成功删除商品：" + product.getName());
                return;
            }
        }
        System.out.println("未找到名称为" + name + "的商品！");
    }

    public void searchProduct(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                System.out.println("查询到商品：" + product.getName() + "，价格：" + product.getPrice());
                return;
            }
        }
        System.out.println("未找到名称为" + name + "的商品！");
    }
}