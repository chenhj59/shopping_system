package org.example.User;

import java.util.Random;
import java.util.ArrayList;

import org.example.Account;
import org.example.MD5Encryption;

import java.sql.*;

public class User extends Account{
    // 正则表达式，验证密码是否符合格式
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()-_=+[{]};:'\",<.>/?])[a-zA-Z\\d!@#$%^&*()-_=+[{]};:'\",<.>/?]{8,}$";
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String PUNCTUATIONS = "!@#$%^&*()-_=+[{]};:'\",<.>/?";
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

    public static boolean validatePassword(String password) {
        return (password.length()>8 && password.matches(PASSWORD_PATTERN));
    }

    public void register(ArrayList<User> users, User user){
        users.add(user);
    }

    @Override
    public boolean login(String username, String password) {
        return true;
        }

    public int login(ArrayList<User> users, User u, String username, String password) {
        int idx = 0;
        for(User user : users){
            if(user.getUsername().equals(username)){
                if(user.getPassword().equals(password)){
                    u.setID(user.getID());
                    return idx;
                }else{
                    return -1;
                }
            }
            idx++;
        }
        return -1;
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

    public static String generatePassword(int length) {
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        // 添加至少一个小写字母
        password.append(getRandomCharacter(LOWER_CASE, random));

        // 添加至少一个大写字母
        password.append(getRandomCharacter(UPPER_CASE, random));

        // 添加至少一个数字
        password.append(getRandomCharacter(DIGITS, random));

        // 添加至少一个标点符号
        password.append(getRandomCharacter(PUNCTUATIONS, random));

        // 添加剩余字符
        for (int i = 0; i < length - 4; i++) {
            String characters = LOWER_CASE + UPPER_CASE + DIGITS + PUNCTUATIONS;
            password.append(getRandomCharacter(characters, random));
        }

        // 打乱密码字符顺序
        String shuffledPassword = shufflePassword(password.toString(), random);

        return shuffledPassword;
    }

    private static char getRandomCharacter(String characters, Random random) {
        int index = random.nextInt(characters.length());
        return characters.charAt(index);
    }

    private static String shufflePassword(String password, Random random) {
        StringBuilder shuffledPassword = new StringBuilder(password);
        for (int i = shuffledPassword.length() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = shuffledPassword.charAt(i);
            shuffledPassword.setCharAt(i, shuffledPassword.charAt(j));
            shuffledPassword.setCharAt(j, temp);
        }
        return shuffledPassword.toString();
    }

    public void changePassword(ArrayList<User> users, int idx, String username, String password){
        users.get(idx).setPassword(password);
    }

    public int verification(ArrayList<User> users, User u) {
        int idx = 0;
        for(User user : users){
            if(user.getUsername().equals(u.getUsername())){
                if(user.getEmail().equals(u.getEmail())){
                    u.setID(user.getID());
                    return idx;
                }else{
                    System.out.println("邮箱与用户名绑定不一致！");
                    return -1;
                }
            }
            idx++;
        }
        System.out.println("用户名不存在！");
        return -1;
    }
    public void resetPassword(ArrayList<User> users, int idx){
        String password = generatePassword(9);
        System.out.println("邮箱模拟：已经发送密码到" + users.get(idx).getEmail());
        System.out.println("密码是：" + password);
        password = MD5Encryption.encrypt(password);
        users.get(idx).setPassword(password);
    }
    public void logout() {
        // 实现退出登录逻辑
        System.out.println("Logout successfully.");
    }
}
