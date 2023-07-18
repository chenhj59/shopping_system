package org.example.Admin;

import org.example.Account;

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

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void changePassword(String username, String newPassword) {
        // 实现密码管理逻辑，修改管理员密码
        this.password = newPassword;
    }

    public void logout() {
        // 实现退出登录逻辑
        System.out.println("Logout successfully.");
    }

}

