package org.example;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class User {
    private static final String DB_URL = "jdbc:sqlite:usersAccount.db";
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

    public void register(String username, String password, String name, String age, String sex){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement prep = conn.prepareStatement("INSERT INTO usersAccount(username, password, name, age, sex) values (?, ?, ?, ?, ?)");
            prep.setString(1, username);
            prep.setString(2, password);
            prep.setString(3, name);
            prep.setString(4, age);
            prep.setString(5, sex);
            
            prep.executeUpdate();
            System.out.println("注册成功！");
        }catch (SQLException e) {
            System.out.println("初始化数据库失败: " + e.getMessage());
        } catch(ClassNotFoundException e){
            System.out.println("加载JDBC失败: " + e.getMessage());
        }
    }

    public boolean login(String username, String password) {
        ResultSet resultSet = null;
        try(Connection connection = DriverManager.getConnection(DB_URL)){
            PreparedStatement prep = connection.prepareStatement("SELECT * FROM usersAccount WHERE username = ?");
            prep.setString(1, username);
            resultSet = prep.executeQuery();

            if(resultSet.next()){
                if(resultSet.getString(2).equals(password)){
                    System.out.println("登录成功！");
                    return true;
                }
                else{
                    System.out.println("登录失败！");
                    return false;
                }
            }
            return false;
        } catch(SQLException e){
            System.out.println("初始化数据库失败: " + e.getMessage());
            return false;
        }
    }

    public String getUsername() {
        return this.username;
    }

    public void setUserName(String username) {
        this.username = username;
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
