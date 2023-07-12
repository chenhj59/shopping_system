package org.example;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminUserManager {
    private List<User> customers;
    private static final String DB_URL = "jdbc:sqlite:users.db";

    public void listAllCustomers() throws SQLException{
        if (customers.isEmpty()) {
            System.out.println("没有客户信息！");
        } else {
            System.out.println("所有客户信息：");
            for (User customer : customers) {
                System.out.println("姓名：" + customer.getUsername());
            }
        }
    }

    public boolean deleteCustomer(String name) throws SQLException{
        try(Connection connection = DriverManager.getConnection(DB_URL)){
            PreparedStatement prep = connection.prepareStatement("DELETE  FROM users WHERE username = ?");
            prep.setString(1, name);
            prep.executeUpdate();

            System.out.println("删除" + name + "信息成功！");

            return true;
        } catch(SQLException e){
            System.out.println("未找到姓名为" + name + "的客户！");

            return false;
        }
    }

    public boolean searchCustomer(String name) throws SQLException{
        ResultSet resultSet = null;
        try(Connection connection = DriverManager.getConnection(DB_URL)){
            PreparedStatement prep = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            prep.setString(1, name);
            resultSet = prep.executeQuery();

            System.out.println(name + "的信息：");

            return true;
        } catch(SQLException e){
            System.out.println("未找到姓名为" + name + "的客户！");

            return false;
        }
    }
}
