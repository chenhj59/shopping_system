package org.example.User;
import java.util.ArrayList;
import java.util.List;

import org.example.Account;

import java.sql.*;

public class User extends Account{
    public final String DB_URL = "jdbc:sqlite:usersAccount.db";
    public int userID = 0;
    public String userLevel = null;
    public String registerTime = null;
    public double totalCost = 0.0;
    public String userPhoneNumber = null;
    public String userEmail = null;

    public User()
    {

    }
    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public User(int ID, String name, String password, String userLevel, String registerTime, double totalCost, String userPhoneNumber, String userEmail){
        this.userID = ID;
        this.username = name;
        this.password = password;
        this.userLevel = userLevel;
        this.registerTime = registerTime;
        this.totalCost = totalCost;
        this.userPhoneNumber = userPhoneNumber;
        this.userEmail = userEmail;
    }

    public void register(ArrayList<User> users, User user){
        users.add(user);
    }

    @Override
    public boolean login(String username, String password) {
        return true;
        }

    public boolean login(ArrayList<User> users, User u, String username, String password) {
        for(User user : users){
            if(user.getUsername().equals(username)){
                if(user.getPassword().equals(password)){
                    user = u;
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    } 

    public int getID(){
        return this.userID;
    }
    @Override
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUserLevel() {
        return this.userLevel;
    }

    public String getRegisterTime() {
        return this.registerTime;
    }

    public double getTotalCost() {
        return this.totalCost;
    }

    public String getPhoneNumber() {
        return this.userPhoneNumber;
    }

    public String getEmail() {
        return this.userEmail;
    }

    public void setID(int ID){
        this.userID = ID;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserLevel(String level) {
        this.userLevel = level;
    }

    public void setRegisterTime(String time) {
        this.registerTime = time;
    }

    public void setTotalCost(double cost) {
        this.totalCost = cost;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.userPhoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.userEmail = email;
    }

    public void changePassword(String username, String newPassword) {
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement prep = conn.prepareStatement("UPDATE usersAccount SET password=? WHERE username=?");
            prep.setString(1, username);
            prep.setString(2, newPassword);

            int rowsUpdated = prep.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("修改成功！");
            } else {
                System.out.println("修改失败！");
            }
        }catch (SQLException e) {
            System.out.println("初始化数据库失败: " + e.getMessage());
        } catch(ClassNotFoundException e){
            System.out.println("加载JDBC失败: " + e.getMessage());
        }
    }

    public void logout() {
        // 实现退出登录逻辑
        System.out.println("Logout successfully.");
    }
}
