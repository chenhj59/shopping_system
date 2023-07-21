package org.example;

import org.example.User.User;

import java.util.ArrayList;

public abstract class Account {
    public String username = null;
    public String password = null;

    public abstract String getUsername();

    public abstract void setUsername(String username);

    public abstract void changePassword(String username, String newPassword);

    public abstract void resetPassword(ArrayList<User> users, int idx);

    public abstract boolean login(String username, String password);

    public abstract void logout();
}
