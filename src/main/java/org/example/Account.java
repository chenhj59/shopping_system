package org.example;

public abstract class Account {
    protected String username;
    protected String password;

    public abstract String getUsername();

    public abstract void setUserName(String username);

    public abstract void changePassword(String username, String newPassword);

    public abstract boolean login(String username, String password);

    public abstract void logout();
}
