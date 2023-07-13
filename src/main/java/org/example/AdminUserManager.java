package org.example;

import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminUserManager {
    private List<User> customers;
    private static final String DB_URL = "jdbc:sqlite:usersAccount.db";

    public void listAllCustomers(String position){
        try(Connection connection = DriverManager.getConnection(DB_URL)){
            String sql = "SELECT * FROM usersAccount WHERE " + position;
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while(resultSet.next()){
                System.out.print("用户名：" + resultSet.getString(2) + " ");
                System.out.print("密码：" + resultSet.getString(3) + " ");
                System.out.print("名字：" + resultSet.getString(4) + " ");
                System.out.print("年龄：" + resultSet.getString(5) + " ");
                System.out.print("性别：" + resultSet.getString(6) + " ");
                System.out.println();
            }
        } catch(SQLException e){
            System.out.println("初始化数据库失败: " + e.getMessage());
        }
    }

    public boolean deleteCustomer(String name){
        try(Connection connection = DriverManager.getConnection(DB_URL)){
            PreparedStatement prep = connection.prepareStatement("DELETE FROM usersAccount WHERE username = ?");
            prep.setString(1, name);
            prep.executeUpdate();

            System.out.println("删除" + name + "信息成功！");

            return true;
        } catch(SQLException e){
            System.out.println("未找到姓名为" + name + "的客户！");

            return false;
        }
    }

    public boolean searchCustomer(String name){
        ResultSet resultSet = null;
        try(Connection connection = DriverManager.getConnection(DB_URL)){
            PreparedStatement prep = connection.prepareStatement("SELECT * FROM usersAccount WHERE username = ?");
            prep.setString(1, name);
            resultSet = prep.executeQuery();

            if(resultSet.next()){
                System.out.print("用户名：" + resultSet.getString(2) + " ");
                System.out.print("密码：" + resultSet.getString(3) + " ");
                System.out.print("名字：" + resultSet.getString(4) + " ");
                System.out.print("年龄：" + resultSet.getString(5) + " ");
                System.out.print("性别" + resultSet.getString(6) + " ");
                System.out.println();
            }
            return true;
        } catch(SQLException e){
            System.out.println("未找到姓名为" + name + "的客户！");

            return false;
        }
    }
}
