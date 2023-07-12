package org.example;
import java.sql.*;
import java.util.Scanner;

public class MainShoppingSystem {
    public static void main(String[] args) {
        UserDatebaseInitializer userDatebaseInitializer = new UserDatebaseInitializer();
        userDatebaseInitializer.initializeDatabase();

        ProductDatebaseInitializer productDateInitializer = new ProductDatebaseInitializer();
        productDateInitializer.initializeDatabase();

        User user = new User();
        UserShoppingAction userShoppingAction = new UserShoppingAction();

        Admin admin = new Admin();
        AdminUserManager adminUserManager = new AdminUserManager();
        AdminProductManager adminProductManager = new AdminProductManager();

        Scanner scanner = new Scanner(System.in);
        String role;
        System.out.println("请选择角色：管理员（A）或用户（U）");
        role = scanner.nextLine();

        if (role.equalsIgnoreCase("A")) {
            System.out.println("请选择操作：");
            System.out.println("1. 登录");
            System.out.println("2. 注册");
            int choice = scanner.nextInt();
            String adminname;
            String password;
            switch(choice)
            {
                case 1:
                    System.out.println("请输入管理员用户名：");
                    adminname = scanner.nextLine();
                    System.out.println("请输入管理员密码：");
                    password = scanner.nextLine();
                    user.register(adminname, password);
                    break;
                case 2:
                    System.out.println("请输入管理员用户名：");
                    adminname = scanner.nextLine();
                    System.out.println("请输入管理员密码：");
                    password = scanner.nextLine();
        
                    if (admin.login(adminname, password)) {
                        System.out.println("管理员登录成功！");
                        while (true) {
                            System.out.println("请选择操作：");
                            System.out.println("1. 管理顾客");
                            System.out.println("2. 管理商品");
                            System.out.println("3. 修改密码");
                            System.out.println("4. 退出登录");
        
                            choice = scanner.nextInt();
                            scanner.nextLine(); // 读取换行符
        
                            switch (choice) {
                                case 1:
                                    
                                    break;
                                case 2:
                                    
                                    break;
                                case 3:
                                    System.out.println("请输入新密码：");
                                    String newPassword = scanner.nextLine();
                                    admin.changePassword(newPassword);
                                    break;
                                case 4:
                                    admin.logout();
                                    return;
                                default:
                                    System.out.println("无效的选择！");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("管理员登录失败！");
                    }
                    break;
            }
        } else if (role.equalsIgnoreCase("U")) {
            System.out.println("请输入用户用户名：");
            String username = scanner.nextLine();
            System.out.println("请输入用户密码：");
            String password = scanner.nextLine();

            if (user.login(username, password)) {
                System.out.println("用户登录成功！");

                while (true) {
                    System.out.println("请选择操作：");
                    System.out.println("1. 购物");
                    System.out.println("2. 修改密码");
                    System.out.println("3. 退出登录");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // 读取换行符

                    switch (choice) {
                        case 1:
                            
                            break;
                        case 2:
                            System.out.println("请输入新密码：");
                            String newPassword = scanner.nextLine();
                            user.changePassword(newPassword);
                            break;
                        case 3:
                            user.logout();
                            return;
                        default:
                            System.out.println("无效的选择！");
                            break;
                    }
                }
            } else {
                System.out.println("用户登录失败！");
            }
        } else {
            System.out.println("无效的角色选择！");
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