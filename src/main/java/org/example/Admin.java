package org.example;

public class Admin {
    private String username;
    private String password;

    public boolean login(String username, String password) {
        // 验证管理员用户名和密码的正确性
        if ("admin".equals(username) && "admin123".equals(password)) {
            return true; // 登录成功返回true
        }
        return false; // 登录失败返回false
    }

    public void changePassword(String newPassword) {
        // 实现密码管理逻辑，修改管理员密码
        this.password = newPassword;
    }

    public void logout() {
        // 实现退出登录逻辑
        System.out.println("Logout successfully.");
    }

}

