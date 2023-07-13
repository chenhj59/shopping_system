package org.example;
import java.sql.*;
import java.util.Scanner;

import org.example.Admin.Admin;
import org.example.Admin.AdminProductManager;
import org.example.Admin.AdminUserManager;
import org.example.User.User;
import org.example.User.UserDatebaseInitializer;
import org.example.User.UserShoppingAction;

import java.util.ArrayList;
import java.util.List;

public class MainShoppingSystem {
    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<Product>();
        UserDatebaseInitializer userDatebaseInitializer = new UserDatebaseInitializer();
        userDatebaseInitializer.initializeDatabase();

        ProductDatebaseInitializer productDateInitializer = new ProductDatebaseInitializer();
        productDateInitializer.initializeDatabase();
        productDateInitializer.read(products);

        UserShoppingAction userShoppingAction = new UserShoppingAction();

        Admin admin = new Admin();
        AdminUserManager adminUserManager = new AdminUserManager();
        AdminProductManager adminProductManager = new AdminProductManager();

        Product product = new Product();

        CartItem cartItem = new CartItem();

        QueryParams queryParams = new QueryParams();

        Scanner scanner = new Scanner(System.in);
        int isReturn = 0; //是否返回上一级
        String role = "";
        String choice = "";
        String userInput = "";
        int exit = 0;
        while(true){
            if(exit == 1){
                break;
            }
            System.out.println("请选择角色：管理员（A）或用户（U）");
            role = scanner.nextLine();
            while(true){
                if (role.equalsIgnoreCase("A")) {
                    String adminname;
                    String password;
                    System.out.print("当前为管理员登录界面，若返回上一级请按q，否则按任意键继续 >");
                    choice = scanner.nextLine();
                    if(choice.equals("q"))
                    {
                        break;
                    }
                    System.out.println("请输入管理员用户名：");
                    adminname = scanner.nextLine();
                    System.out.println("请输入管理员密码：");
                    password = scanner.nextLine();
                        if (admin.login(adminname, password)) {
                            System.out.println("管理员登录成功！");
                            while (true) {
                                System.out.println("请选择操作：");
                                System.out.println("1. 管理客户");
                                System.out.println("2. 管理商品");
                                System.out.println("3. 修改密码");
                                System.out.println("4. 退出登录");
                                System.out.print("当前为管理员选择操作界面，若返回上一级请按q >");
                                choice = scanner.nextLine();
            
                                switch (choice) {
                                    case "1":
                                    while(true){
                                        System.out.println("请选择操作：");
                                        System.out.println("1. 列出所有客户信息");
                                        System.out.println("2. 删除客户信息");
                                        System.out.println("3. 查询客户信息");
                                        System.out.print("当前为管理员管理客户界面，若返回上一级请按q >");
                                        choice = scanner.nextLine();
                                        switch(choice){
                                            case "1":
                                                adminUserManager.listAllCustomers("1");
                                                break;
                                            case "2":
                                                System.out.print("请给出要删除客户的用户名：");
                                                String deleteName = scanner.nextLine();
                                                adminUserManager.deleteCustomer(deleteName);
                                                break;
                                            case "3":
                                                System.out.print("请给出要搜索客户的用户名：");
                                                String searchName = scanner.nextLine();
                                                adminUserManager.searchCustomer(searchName);
                                                break;
                                            case "q":
                                                isReturn = 1;
                                                break;
                                            default:
                                                System.out.println("无效的选择！");
                                                break;
                                            }
                                            if(isReturn == 1)
                                            {
                                                isReturn = 0;
                                                break;
                                            }
                                        }
                                        break;
                                    case "2":
                                    while(true){
                                        System.out.println("请选择操作：");
                                        System.out.println("1. 列出所有商品信息");
                                        System.out.println("2. 添加商品信息");
                                        System.out.println("3. 修改商品信息");
                                        System.out.println("4. 删除商品信息");
                                        System.out.println("5. 查询商品信息");
                                        System.out.print("当前为管理员管理商品界面，若返回上一级请按q >");
                                        choice = scanner.nextLine();
                                        switch(choice){
                                            case "1":
                                            adminProductManager.listAllProducts(products);
                                                break;
                                            case "2":
                                                System.out.print("请给出要添加的商品编号：");
                                                product.setID(scanner.nextInt());
                                                scanner.nextLine();
                                                System.out.print("请给出要添加的商品名称：");
                                                product.setName(scanner.nextLine());
                                                System.out.print("请给出要添加的商品得生产厂家：");
                                                product.setManufacturer(scanner.nextLine());
                                                System.out.print("请给出要添加的商品的生产日期：");
                                                product.setDateOfManufacture(scanner.nextLine());
                                                System.out.print("请给出要添加的商品的型号：");
                                                product.setModel(scanner.nextLine());
                                                System.out.print("请给出要添加的商品的进货价格：");
                                                product.setPurcPrice(scanner.nextDouble());
                                                scanner.nextLine();
                                                System.out.print("请给出要添加的商品的零售价格：");
                                                product.setRetailPrice(scanner.nextDouble());
                                                scanner.nextLine();
                                                System.out.print("请给出要添加的商品的数量：");
                                                product.setInventory(scanner.nextInt());
                                                scanner.nextLine();  //吸收回车键
                                                adminProductManager.addProduct(products, product);
                                                break;
                                            case "3":
                                            System.out.print("请给出要修改的商品编号：");
                                            product.setID(scanner.nextInt());
                                            scanner.nextLine();
                                            System.out.print("请给出要修改的商品名称：");
                                            product.setName(scanner.nextLine());
                                            System.out.print("请给出要修改的商品得生产厂家：");
                                            product.setManufacturer(scanner.nextLine());
                                            System.out.print("请给出要修改的商品的生产日期：");
                                            product.setDateOfManufacture(scanner.nextLine());
                                            System.out.print("请给出要修改的商品的型号：");
                                            product.setModel(scanner.nextLine());
                                            System.out.print("请给出要修改的商品的进货价格：");
                                            product.setPurcPrice(scanner.nextDouble());
                                            scanner.nextLine();
                                            System.out.print("请给出要修改的商品的零售价格：");
                                            product.setRetailPrice(scanner.nextDouble());
                                            scanner.nextLine();
                                            System.out.print("请给出要修改的商品的数量：");
                                            product.setInventory(scanner.nextInt());
                                            scanner.nextLine();  //吸收回车键
                                                adminProductManager.updateProduct(products, product);
                                                break;
                                            case "4":
                                                System.out.print("请给出要删除的商品名称：");
                                                String deleteName = scanner.nextLine();
                                                adminProductManager.deleteProduct(products, deleteName);
                                                break;
                                            case "5":
                                                System.out.print("请给出要查询的商品名称：");
                                                queryParams.setName(scanner.nextLine());
                                                System.out.print("请给出要查询的商品的生产厂家：");
                                                queryParams.setManu(scanner.nextLine());
                                                System.out.print("请给出要查询的商品的零售价格：");
                                                queryParams.setRetPrice(scanner.nextDouble());
                                                scanner.nextLine();
                                                adminProductManager.searchProduct(products, queryParams);
                                                break;
                                            case "q":
                                                isReturn = 1;
                                                break;
                                            default:
                                                System.out.println("无效的选择！");
                                                break;
                                            }
                                            if(isReturn == 1)
                                            {
                                                isReturn = 0;
                                                break;
                                            }
                                        }                                       
                                        break;
                                    case "3":
                                        System.out.print("当前为管理员修改密码界面，若返回上一级请按q，否则按任意键继续 >");
                                        choice = scanner.nextLine();
                                        if(choice.equals("q"))
                                        {
                                            break;
                                        }
                                        System.out.println("请输入新密码：");
                                        String newPassword = scanner.nextLine();
                                        admin.changePassword(adminname, newPassword);
                                        break;
                                    case "4":
                                        admin.logout();
                                        isReturn = 1;
                                        break;
                                    case "q":
                                        isReturn = 1;
                                        break;
                                    default:
                                        System.out.println("无效的选择！");
                                        break;
                                }
                                if(isReturn == 1)
                                {
                                    isReturn = 0;
                                    break;
                                }
                            }
                        } else {
                            System.out.println("管理员登录失败！");                            
                        }
                } else if (role.equalsIgnoreCase("U")) {
                    /*System.out.println("请选择操作：");
                    System.out.println("1. 注册");
                    System.out.println("2. 登录");
                    System.out.print("当前为用户选择操作界面，若返回上一级请按q >");
                    choice = scanner.nextLine();
                    if(choice.equals("q"))
                    {
                        break;
                    }
                    String password="";
                    String username="";
                    String name="";
                    String age="";
                    String sex="";                
                    while(true){
                        switch(choice){
                            case "1":
                                System.out.print("当前为用户注册界面，若返回上一级请按q，否则按任意键继续 >");
                                choice = scanner.nextLine();
                                if(choice.equals("q"))
                                {
                                    break;
                                }
                                System.out.println("请输入用户名：");
                                username = scanner.nextLine();
                                System.out.println("请输入密码：");
                                password = scanner.nextLine();
                                System.out.println("请输入姓名：");
                                name = scanner.nextLine();
                                System.out.println("请输入年龄：");
                                age = scanner.nextLine();
                                System.out.println("请输入性别：");
                                sex = scanner.nextLine();
                                user.register(username, password, name, age, sex); // 不加break，因为注册成功直接进入登录界面
                            case "2":
                            while(true){
                                System.out.print("当前为用户登录界面，若返回上一级请按q，否则按任意键继续 >");
                                choice = scanner.nextLine();
                                if(choice.equals("q"))
                                {
                                    break;
                                }
                                System.out.println("请输入用户名：");
                                username = scanner.nextLine();
                                System.out.println("请输入用户密码：");
                                password = scanner.nextLine();
                                if (user.login(username, password)) {               
                                    while (true) {
                                        System.out.println("请选择操作：");
                                        System.out.println("1. 购物");
                                        System.out.println("2. 修改密码");
                                        System.out.println("3. 退出登录");
                                        System.out.print("当前为用户选择操作界面，若返回上一级请按q >");                
                                        choice = scanner.nextLine();
            
                                        switch (choice) {
                                            case "1":
                                            while(true){
                                                System.out.println("请选择操作：");
                                                System.out.println("1. 将商品加入购物车");
                                                System.out.println("2. 从购物车移除商品");
                                                System.out.println("3. 修改购物车中商品");
                                                System.out.println("4. 结账");
                                                System.out.println("5. 查看购物记录");
                                                System.out.print("当前为购物界面，若返回上一级请按q >");
                                                choice = scanner.nextLine();
                                                switch(choice){
                                                    case "1":
                                                        System.out.print("请给出要添加的商品名：");
                                                        product.setName(scanner.nextLine());    
                                                        System.out.print("请给出你需要购买数量：");
                                                        product.setQunatity(scanner.nextInt());  
                                                        scanner.nextLine();                                                  
                                                        userShoppingAction.addItem(username, product);;
                                                        break;
                                                    case "2":
                                                        System.out.print("请给出要移除的商品名：");
                                                        product.setName(scanner.nextLine());
                                                        userShoppingAction.removeItem(product);
                                                        break;
                                                    case "3":
                                                        System.out.print("请给出要修改的商品名：");
                                                        product.setName(scanner.nextLine());
                                                        System.out.print("请给出你需要购买数量：");
                                                        product.setQunatity(scanner.nextInt());
                                                        scanner.nextLine();
                                                        userShoppingAction.updateQuantity(product);
                                                        break;
                                                    case "4":
                                                        userShoppingAction.checkout(username);
                                                        break;
                                                    case "5":
                                                        userShoppingAction.viewShoppingHistory(username);
                                                        break;
                                                    case "q":
                                                        isReturn = 1;
                                                        break;
                                                    default:
                                                        System.out.println("无效的选择！");
                                                        break;
                                                    }
                                                    if(isReturn == 1)
                                                    {
                                                        isReturn = 0;
                                                        break;
                                                    }
                                                }        
                                                break;
                                            case "2":
                                                System.out.print("当前为用户修改密码界面，若返回上一级请按q，否则按任意键继续 >");
                                                choice = scanner.nextLine();
                                                if(choice.equals("q"))
                                                {
                                                    break;
                                                }
                                                System.out.println("请输入新密码：");
                                                String newPassword = scanner.nextLine();
                                                user.changePassword(username, newPassword);
                                                break;
                                            case "3":
                                                user.logout();
                                                isReturn = 1;
                                                break;
                                            case "q":
                                                isReturn = 1;
                                                break;
                                            default:
                                                System.out.println("无效的选择！");
                                                break;
                                        }
                                        if(isReturn == 1)
                                        {
                                            isReturn = 0;
                                            break;
                                        }
                                    }
                                } 
                            } 
                            break; 
                            case "q":
                                isReturn = 1;
                                break;
                            default:
                                System.out.println("无效的选择！");
                                isReturn = 1;
                                break; 
                        }
                        if(isReturn == 1)
                        {
                            isReturn = 0;
                            break;
                        }  
                    }*/
                } else {
                    System.out.println("无效的角色选择！");
                    break;
                }
            }
            productDateInitializer.write(products);
        }
    }

    private static void help() {
        System.out.print("欢迎进入帮助子菜单");

        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        while(true) {
            System.out.println("请输入你的指令,q 退出");
            System.out.print("你当前在 help 的二级子目录下 >");
            userInput = scanner.nextLine();

            if (userInput.equals("q")) {
                break;
            }

            System.out.println("其实吧，这个也就是做个样子给你看看，让你知道怎么做二级界面罢了");
        }
    }
}