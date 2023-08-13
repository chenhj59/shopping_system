package org.example;

import java.util.Scanner;

import javax.xml.datatype.DatatypeFactory;

import java.util.Date;
import java.util.ArrayList;

import java.text.SimpleDateFormat;

import org.example.Admin.Admin;
import org.example.Admin.AdminDatabaseStorage;
import org.example.Admin.AdminProductManager;
import org.example.Admin.AdminUserManager;
import org.example.Product.Product;
import org.example.Product.ProductDatabaseStorage;
import org.example.Product.ProductQueryParams;
import org.example.User.CartItem;
import org.example.User.PurchaseItem;
import org.example.User.User;
import org.example.User.UserDatabaseStorage;
import org.example.User.UserQueryParams;
import org.example.User.UserShoppingAction;

public class MainShoppingSystem {
    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<Product>();
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<Admin> admins = new ArrayList<Admin>();
        ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
        ArrayList<PurchaseItem> purchaseItems = new ArrayList<PurchaseItem>();

        UserDatabaseStorage userDatebaseInitializer = new UserDatabaseStorage();
        userDatebaseInitializer.initializeDatabase();
        userDatebaseInitializer.read(users, cartItems, purchaseItems);

        DataStorage adminDataStorage = DataStorageFactory.createAdminDataStorage("Database");
        adminDataStorage.initializeDatabase();
        adminDataStorage.read(admins);

        DataStorage productDataStorage = DataStorageFactory.createProductDataStorage("Database");
        productDataStorage.initializeDatabase();
        productDataStorage.read(products);

        Admin admin = new Admin();
        AdminUserManager adminUserManager = new AdminUserManager();
        AdminProductManager adminProductManager = new AdminProductManager();

        User user = new User();
        UserQueryParams userQueryParams = new UserQueryParams();
        UserShoppingAction userShoppingAction = new UserShoppingAction();

        Product product = new Product();
        ProductQueryParams queryParams = new ProductQueryParams();

        Scanner scanner = new Scanner(System.in);

        int isReturn = 0; //是否返回上一级
        int flag = 0; //判断数字是否输入格式正确
        int quantity = 0;
        String role = "";
        String choice = "";
        while(true){
            System.out.println("想要终止程序，请输入exit");
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
                    }else if(choice.equals("exit")){
                        isExit(productDataStorage, userDatebaseInitializer, adminDataStorage, products, users, cartItems, purchaseItems, admins);
                    }
                    System.out.println("请输入管理员用户名：");
                    adminname = scanner.nextLine();
                    System.out.println("请输入管理员密码：");
                    password = scanner.nextLine();
                    int adminIdx = admin.login(admins, admin, adminname, password);
                        if (adminIdx != -1) {
                            System.out.println("管理员登录成功！");
                            while (true) {
                                System.out.println("请选择操作：");
                                System.out.println("1. 管理客户");
                                System.out.println("2. 管理商品");
                                System.out.println("3. 密码管理");
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
                                                adminUserManager.listAllCustomers(users);
                                                break;
                                            case "2":
                                                System.out.print("请给出要删除客户的用户名：");
                                                String deleteName = scanner.nextLine();
                                                adminUserManager.deleteCustomer(users, deleteName);
                                                break;
                                            case "3":
                                                while(true){
                                                    try{
                                                        System.out.println("请给出要搜索客户的ID：");
                                                        userQueryParams.setID(Integer.valueOf(scanner.nextLine()));
                                                        break;
                                                    }catch (NumberFormatException e) {
                                                        System.out.println("输入无效，请输入一个有效的数字。");
                                                    }
                                                } 
                                                System.out.print("请给出要搜索客户的用户名：");
                                                userQueryParams.setName(scanner.nextLine());
                                                adminUserManager.searchCustomer(users, userQueryParams, true);
                                                break;
                                            case "q":
                                                isReturn = 1;
                                                break;
                                            case "exit":
                                                isExit(productDataStorage, userDatebaseInitializer, adminDataStorage, products, users, cartItems, purchaseItems, admins);
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
                                                Product pro = new Product();
                                                while(true){
                                                    try{
                                                        System.out.print("请给出要添加的商品编号：");
                                                        pro.setID(Integer.valueOf(scanner.nextLine())); 
                                                        break;
                                                    }catch (NumberFormatException e) {
                                                        System.out.println("输入无效，请输入一个有效的数字。");
                                                    }
                                                } 
                                                System.out.print("请给出要添加的商品名称：");
                                                pro.setName(scanner.nextLine());
                                                System.out.print("请给出要添加的商品得生产厂家：");
                                                pro.setManufacturer(scanner.nextLine());
                                                System.out.print("请给出要添加的商品的生产日期：");
                                                pro.setDateOfManufacture(scanner.nextLine());
                                                System.out.print("请给出要添加的商品的型号：");
                                                pro.setModel(scanner.nextLine());
                                                while(true){
                                                    System.out.print("请给出要添加的商品的进货价格：");                                      
                                                    try {
                                                        pro.setPurcPrice(Double.parseDouble(scanner.nextLine()));
                                                        break;
                                                    } catch (NumberFormatException e) {
                                                        System.out.println("输入无效，请输入一个有效的数字。");
                                                    }
                                                }
                                                while(true){
                                                    System.out.print("请给出要添加的商品的零售价格：");                                    
                                                    try {
                                                        pro.setRetailPrice(Double.parseDouble(scanner.nextLine()));
                                                        break;
                                                    } catch (NumberFormatException e) {
                                                        System.out.println("输入无效，请输入一个有效的数字。");
                                                    }
                                                }
                                                while(true){
                                                    try{
                                                        System.out.print("请给出要添加的商品的数量：");
                                                        pro.setInventory(Integer.valueOf(scanner.nextLine()));
                                                        break;
                                                    }catch (NumberFormatException e) {
                                                        System.out.println("输入无效，请输入一个有效的数字。");
                                                    }
                                                } 
                                                adminProductManager.addProduct(products, pro);
                                                break;
                                            case "3":
                                            while(true){
                                                try{
                                                    System.out.print("请给出要修改的商品编号：");
                                                    product.setID(Integer.valueOf(scanner.nextLine())); 
                                                    break;
                                                }catch (NumberFormatException e) {
                                                    System.out.println("输入无效，请输入一个有效的数字。");
                                                }
                                            } 
                                            System.out.print("请给出要修改的商品名称：");
                                            product.setName(scanner.nextLine());
                                            System.out.print("请给出要修改的商品得生产厂家：");
                                            product.setManufacturer(scanner.nextLine());
                                            System.out.print("请给出要修改的商品的生产日期：");
                                            product.setDateOfManufacture(scanner.nextLine());
                                            System.out.print("请给出要修改的商品的型号：");
                                            product.setModel(scanner.nextLine());
                                            while(true){
                                                System.out.print("请给出要修改的商品的进货价格：");
                                                try {
                                                    product.setPurcPrice(Double.parseDouble(scanner.nextLine()));
                                                    break;
                                                } catch (NumberFormatException e) {
                                                    System.out.println("输入无效，请输入一个有效的数字。");
                                                }
                                            }
                                            while(true){
                                                System.out.print("请给出要修改的商品的零售价格：");
                                                try {
                                                    product.setRetailPrice(Double.parseDouble(scanner.nextLine()));
                                                    break;
                                                } catch (NumberFormatException e) {
                                                    System.out.println("输入无效，请输入一个有效的数字。");
                                                }
                                            }
                                            while(true){
                                                try{
                                                    System.out.print("请给出要修改的商品的数量：");
                                                    product.setInventory(Integer.valueOf(scanner.nextLine()));
                                                    break;
                                                }catch (NumberFormatException e) {
                                                    System.out.println("输入无效，请输入一个有效的数字。");
                                                }
                                            } 
                                            
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
                                                while(true){
                                                    System.out.print("请给出要查询的商品的零售价格：");
                                                    try {
                                                        queryParams.setRetPrice(Double.parseDouble(scanner.nextLine()));
                                                        break;
                                                    } catch (NumberFormatException e) {
                                                        System.out.println("输入无效，请输入一个有效的数字。");
                                                    }
                                                }
                                                adminProductManager.searchProduct(products, queryParams);
                                                break;
                                            case "q":
                                                isReturn = 1;
                                                break;
                                            case "exit":
                                                isExit(productDataStorage, userDatebaseInitializer, adminDataStorage, products, users, cartItems, purchaseItems, admins);
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
                                        while(true){
                                            System.out.println("请选择操作：");
                                            System.out.println("1. 修改密码");
                                            System.out.println("2. 重置密码");
                                            System.out.print("当前为管理员密码管理界面，若返回上一级请按q >");
                                            choice = scanner.nextLine();
                                            if(choice.equals("q"))
                                            {
                                                break;
                                            }else if(choice.equals("exit")){
                                                isExit(productDataStorage, userDatebaseInitializer, adminDataStorage, products, users, cartItems, purchaseItems, admins);
                                            }
                                            switch(choice){
                                                case "1":
                                                    System.out.print("当前为管理员修改密码界面，若返回上一级请按q，否则按任意键继续 >");
                                                    choice = scanner.nextLine();
                                                    if(choice.equals("q"))
                                                    {
                                                        break;
                                                    }else if(choice.equals("exit")){
                                                        isExit(productDataStorage, userDatebaseInitializer, adminDataStorage, products, users, cartItems, purchaseItems, admins);
                                                    }
                                                    System.out.println("请输入新密码：");
                                                    String newPassword = scanner.nextLine();
                                                    admin.changePassword(admins, adminIdx, adminname, newPassword);
                                                    break;
                                                case "2":
                                                    System.out.print("当前为管理员密码重置界面，若返回上一级请按q，否则按任意键继续 >");
                                                    choice = scanner.nextLine();
                                                    if(choice.equals("q"))
                                                    {
                                                        break;
                                                    }else if(choice.equals("exit")){
                                                        isExit(productDataStorage, userDatebaseInitializer, adminDataStorage, products, users, cartItems, purchaseItems, admins);
                                                    }
                                                    System.out.println("请输入要重置密码的用户名：");
                                                    String username  = scanner.nextLine();
                                                    int userIdx = Admin.searchUser(users, username);
                                                    admin.resetPassword(users, userIdx);
                                                    break;
                                                case "q":
                                                    isReturn = 1;
                                                    break;
                                                case "exit":
                                                    isExit(productDataStorage, userDatebaseInitializer, adminDataStorage, products, users, cartItems, purchaseItems, admins);
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
                                    case "4":
                                        admin.logout();
                                        isReturn = 1;
                                        break;
                                    case "q":
                                        isReturn = 1;
                                        break;
                                    case "exit":
                                        isExit(productDataStorage, userDatebaseInitializer, adminDataStorage, products, users, cartItems, purchaseItems, admins);
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
                    System.out.println("请选择操作：");
                    System.out.println("1. 注册");
                    System.out.println("2. 登录");
                    System.out.println("3. 重置密码");
                    System.out.print("当前为用户选择操作界面，若返回上一级请按q >");
                    choice = scanner.nextLine();
                    if(choice.equals("q"))
                    {
                        break;
                    }else if(choice.equals("exit")){
                        isExit(productDataStorage, userDatebaseInitializer, adminDataStorage, products, users, cartItems, purchaseItems, admins);
                    }
                    String password="";
                    String username="";              
                    while(true){
                        switch(choice){
                            case "1":
                                User u = new User();
                                while(true){
                                    System.out.print("当前为用户注册界面，若返回上一级请按q，否则按任意键继续 >");
                                    choice = scanner.nextLine();
                                    if(choice.equals("q"))
                                    {
                                        break;
                                    }
                                    System.out.println("请输入用户名（要求不少于5个字符）：");
                                    u.setUsername(scanner.nextLine());
                                    if(u.getUsername().length() < 5){
                                        System.out.println("输入用户名格式错误，请重新输入！");
                                        continue;
                                    }
                                    System.out.println("请输入密码（要求大于8个字符，且必须是大小写字母、数字和标点符合的组合）：");
                                    u.setPassword(scanner.nextLine());
                                    if(!User.validatePassword(u.getPassword())){
                                        System.out.println("输入密码格式错误，请重新输入！");
                                        continue;
                                    }
                                    break;
                                }
                                u.setPassword(MD5Encryption.encrypt(u.getPassword()));
                                System.out.println("请输入手机号：");
                                u.setPhoneNumber(scanner.nextLine());
                                System.out.println("请输入邮箱：");
                                u.setEmail(scanner.nextLine());
                                u.setID(users.size()+1);
                                u.setUserLevel("铜牌客户"); //默认初始等级为铜牌客户
                                u.setTotalCost(0.0);
                                Date currentDate = new Date();
                                // 创建 SimpleDateFormat 对象，并定义日期时间格式
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                // 使用 format() 方法将 Date 对象转换为字符串
                                String dateString = dateFormat.format(currentDate);
                                u.setRegisterTime(dateString);
                                user.register(users, u); // 不加break，因为注册成功直接进入登录界面
                            case "2":
                            while(true){
                                System.out.print("当前为用户登录界面，若返回上一级请按q，否则按任意键继续 >");
                                choice = scanner.nextLine();
                                if(choice.equals("q"))
                                {
                                    break;
                                }else if(choice.equals("exit")){
                                    isExit(productDataStorage, userDatebaseInitializer, adminDataStorage, products, users, cartItems, purchaseItems, admins);
                                }
                                System.out.println("请输入用户名：");
                                username = scanner.nextLine();
                                System.out.println("请输入用户密码：");
                                password = scanner.nextLine();
                                password = MD5Encryption.encrypt(password);
                                int userIdx = user.login(users, user, username, password);
                                if (userIdx != -1) {               
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
                                                        while(true){
                                                            try{
                                                                System.out.print("请给出要添加的商品编号：");
                                                                product.setID(Integer.valueOf(scanner.nextLine())); 
                                                                break;
                                                            }catch (NumberFormatException e) {
                                                                System.out.println("输入无效，请输入一个有效的数字。");
                                                            }
                                                        } 
                                                        while(true){
                                                            try{
                                                                System.out.print("请给出你需要购买数量：");
                                                                quantity = Integer.valueOf(scanner.nextLine()); 
                                                                break;
                                                            }catch (NumberFormatException e) {
                                                                System.out.println("输入无效，请输入一个有效的数字。");
                                                            }
                                                        }  
                                                        if(userShoppingAction.searchProduct(products, product) != -1 && product != null){
                                                            userShoppingAction.addItem(cartItems, user.getID(), product, quantity);
                                                        }else{
                                                            System.out.println("搜索不到商品！");
                                                        }                              
                                                        break;
                                                    case "2":
                                                        System.out.print("请给出要移除的商品名：");
                                                        product.setName(scanner.nextLine());
                                                        userShoppingAction.removeItem(cartItems, product);
                                                        break;
                                                    case "3":
                                                        System.out.print("请给出要修改的商品名：");
                                                        product.setName(scanner.nextLine());
                                                        while(true){
                                                            try{
                                                                System.out.print("请给出你需要购买数量：");
                                                                quantity = Integer.valueOf(scanner.nextLine());
                                                                break;
                                                            }catch (NumberFormatException e) {
                                                                System.out.println("输入无效，请输入一个有效的数字。");
                                                            }
                                                        } 
                                                        userShoppingAction.updateQuantity(cartItems, product, quantity);
                                                        break;
                                                    case "4":
                                                        userShoppingAction.checkout(users, products, purchaseItems, cartItems, user.getID(), userIdx);
                                                        break;
                                                    case "5":
                                                        userShoppingAction.viewShoppingHistory(purchaseItems, user.getID());
                                                        break;
                                                    case "q":
                                                        isReturn = 1;
                                                        break;
                                                    case "exit":
                                                        isExit(productDataStorage, userDatebaseInitializer, adminDataStorage, products, users, cartItems, purchaseItems, admins);
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
                                                    System.out.print("当前为用户修改密码界面，若返回上一级请按q，否则按任意键继续 >");
                                                    choice = scanner.nextLine();
                                                    if(choice.equals("q"))
                                                    {
                                                        break;
                                                    }
                                                    System.out.println("请输入新密码：");
                                                    String newPassword = scanner.nextLine();
                                                    if(!User.validatePassword(newPassword)){
                                                        System.out.println("密码格式错误，请重新输入！");
                                                        continue;
                                                    }
                                                    newPassword = MD5Encryption.encrypt(newPassword);
                                                    user.changePassword(users, userIdx, username, newPassword);
                                                    break;
                                                }
                                                break;
                                            case "3":
                                                user.logout();
                                                isReturn = 1;
                                                break;
                                            case "q":
                                                isReturn = 1;
                                                break;
                                            case "exit":
                                            isExit(productDataStorage, userDatebaseInitializer, adminDataStorage, products, users, cartItems, purchaseItems, admins);
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
                            case "3":
                            while(true){
                                System.out.print("当前为用户重置密码界面，若返回上一级请按q，否则按任意键继续 >");
                                choice = scanner.nextLine();
                                if(choice.equals("q"))
                                {
                                    break;
                                }else if(choice.equals("exit")){
                                    isExit(productDataStorage, userDatebaseInitializer, adminDataStorage, products, users, cartItems, purchaseItems, admins);
                                }
                                System.out.print("请给出用户名：");
                                user.setUsername(scanner.nextLine());
                                System.out.print("请给出用户名注册时绑定的邮箱：");
                                user.setEmail(scanner.nextLine());
                                int userIdx = user.verification(users, user);
                                if(userIdx == -1){
                                    continue;
                                }
                                user.resetPassword(users, userIdx);
                                break;
                            }
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
                    }
                } else if(role.equals("exit")){
                    isExit(productDataStorage, userDatebaseInitializer, adminDataStorage, products, users, cartItems, purchaseItems, admins);
                }else {
                    System.out.println("无效的角色选择！");
                    break;
                }
            }
            adminDataStorage.write(admins);
            userDatebaseInitializer.write(users, cartItems, purchaseItems);
            productDataStorage.write(products);
        }
        
    }
    public static void isExit(DataStorage productDataStorage, UserDatabaseStorage userDatebaseInitializer,DataStorage adminDataStorage,  ArrayList<Product> products, ArrayList<User> users, ArrayList<CartItem> cartItems, ArrayList<PurchaseItem> purchaseItems, ArrayList<Admin> admins){
        userDatebaseInitializer.write(users, cartItems, purchaseItems);
        adminDataStorage.write(admins);
        productDataStorage.write(products);
        System.exit(-1);
    }
}