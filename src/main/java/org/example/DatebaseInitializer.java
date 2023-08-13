package org.example;

import java.util.ArrayList;

import org.example.User.User;

public interface DatebaseInitializer<T> {
    //初始化数据库(储存介质)
    public void initializeDatabase();

    public void read(ArrayList<T> lists);

    public void write(ArrayList<T> lists);
}
