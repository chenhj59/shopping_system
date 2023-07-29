package org.example.Admin;

import org.example.User.User;

import org.example.Account;
import org.example.MD5Encryption;

import java.util.ArrayList;
public class Admin extends Account{
    String adminname = null;
    String adminPassword = null;

    public Admin(){

    }

    public Admin(String name, String password){
        this.adminname = name;
        this.adminPassword = password;
    }

    public void setName(String name){
        this.adminname = name;
    }
    public void setPassword(String password){
        this.adminPassword  = password;
    }

    public String getName(){
        return adminname;
    }

    public String getPassword(){
        return adminPassword;
    }
    
    public boolean login(String username, String password) {
        // 验证管理员用户名和密码的正确性
        if ("admin".equals(username) && "admin123".equals(password)) {
            return true; // 登录成功返回true
        }
        return false; // 登录失败返回false
    }

    public int login(ArrayList<Admin> admins, Admin a, String name, String password) {
        int idx = 0;
        for(Admin admin : admins){
            if(admin.getName().equals(name)){
                if(admin.getPassword().equals(password)){
                    return idx;
                }else{
                    return -1;
                }
            }
            idx++;
        }
        return -1;
    } 
    
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void changePassword(String username, String newPassword) {
        // 实现密码管理逻辑，修改管理员密码
        this.password = newPassword;
    }
    public void changePassword(ArrayList<Admin> admins, int idx, String username, String password){
        admins.get(idx).setPassword(password);
    }
    public static int searchUser(ArrayList<User> users, String name){
        int idx = 0;
        for(User user : users){
            if(user.getUsername().equals(name)){
                return idx;
            }
            idx++;
        }
        return -1;
    }
    public void resetPassword(ArrayList<User> users, int idx){
        String password = MD5Encryption.encrypt("User123...");
        users.get(idx).setPassword(password);
    }
    public void logout() {
        // 实现退出登录逻辑
        System.out.println("Logout successfully.");
    }

}

