package org.example;

import java.util.ArrayList;

public interface DataStorage<T> {
    //初始化数据库(储存介质)
    public void initializeDatabase();

    public void read(ArrayList<T> lists);

    public void write(ArrayList<T> lists);
}
