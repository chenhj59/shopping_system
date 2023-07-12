package org.example;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class User {
    private String username;
    private String password;

    User()
    {

    }
    User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public void register(String username, String password) throws SQLException {
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:Datebase.db")){
            Statement stat = conn.createStatement();
            stat.executeUpdate("create table user (name, password);");
            PreparedStatement prep = conn.prepareStatement("insert into user values (?, ?);");
    
            prep.setString(1, username);
            prep.setString(2, password);
            
            prep.executeUpdate();
        System.out.println("User registered successfully.");
        }catch(SQLException e){
            System.out.println("注册失败!");
        }
    }

    public boolean login(String username, String password) {
        // 验证用户名和密码的正确性
        // 实现登录逻辑
        return true; // 登录成功返回true
    }

    public String getUsername() {
        return this.username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public void changePassword(String newPassword) {
        // 实现密码管理逻辑，修改用户密码
        this.password = newPassword;
    }

    public void logout() {
        // 实现退出登录逻辑
        System.out.println("Logout successfully.");
    }
}
