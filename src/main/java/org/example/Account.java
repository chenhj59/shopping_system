package org.example;

import org.example.User.User;

import java.util.ArrayList;

public abstract class Account<T> {
    public String username = null;
    public String password = null;

    public abstract String getUsername();

    public abstract void setUsername(String username);

    public abstract void changePassword(ArrayList<T> lists, int idx, String username, String password);

    public abstract void resetPassword(ArrayList<User> users, int idx);

    public abstract int login(ArrayList<T> lists, T list, String username, String password);

    public abstract void logout();
}
