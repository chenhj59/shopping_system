package org.example.Admin;
import org.example.User.User;
import org.example.User.UserQueryParams;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

public class AdminUserManager {
    public void listAllCustomers(ArrayList<User> users){
        for(User user : users){
            System.out.print("客户ID：" + user.getID() + " ");
            System.out.print("用户名：" + user.getUsername() + " ");
            System.out.print("用户级别：" + user.getUserLevel() + " ");
            System.out.print("用户注册时间：" + user.getRegisterTime() + " ");
            System.out.print("客户累计消费总金额：" + user.getTotalCost() + " ");
            System.out.print("用户手机号" + user.getPhoneNumber() + " ");
            System.out.print("用户邮箱" + user.getEmail() + " ");
            System.out.println();
        }
    }

    public boolean deleteCustomer(ArrayList<User> users, String name){
        Scanner scanner = new Scanner(System.in);
        for(User user : users){
            if(user.getUsername().equals(name)){                
                System.out.println("是否要删除用户信息，删除后将无法恢复，按y确定，按n取消：");
                String flag = scanner.nextLine();
                if(flag.equalsIgnoreCase("y")){
                    users.remove(user);
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

    public boolean searchCustomer(ArrayList<User> users, UserQueryParams queryParams, boolean isOut){
        List<User> results = new ArrayList<User>();
        results = users.stream()
                .filter(user -> !queryParams.hasName() || user.getUsername().equals(queryParams.getName()))
                .filter(user -> !queryParams.hasID() || user.getID() == queryParams.getID())
                .collect(Collectors.toList());
        for(User user : results){
            if(isOut){
                System.out.print("客户ID：" + user.getID() + " ");
                System.out.print("用户名：" + user.getUsername() + " ");
                System.out.print("用户级别：" + user.getUserLevel() + " ");
                System.out.print("用户注册时间：" + user.getRegisterTime() + " ");
                System.out.print("客户累计消费总金额：" + user.getTotalCost() + " ");
                System.out.print("用户手机号" + user.getPhoneNumber() + " ");
                System.out.print("用户邮箱" + user.getEmail() + " ");
                System.out.println();
            }
        }
        return true;
    }
}
