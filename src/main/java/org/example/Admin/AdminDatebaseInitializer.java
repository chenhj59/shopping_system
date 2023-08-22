package org.example.Admin;
import org.example.DatebaseInitializer;

import java.util.ArrayList;

import java.io.*;
public class AdminDatebaseInitializer implements DatebaseInitializer{

    @Override
    public void initializeDatabase() {
        File file = new File("adminAccounts.txt");
        
        try {
            if (!file.exists()) {
                boolean created = file.createNewFile();
                if (created) {
                    System.out.println("文件已创建: " + file.getName());
                } else {
                    System.out.println("无法创建文件。");
                }
            } else {
                System.out.println("文件已存在: " + file.getName());
            }
        } catch (IOException e) {
            System.out.println("发生IO异常: " + e.getMessage());
        }
    }

public void read(ArrayList<Admin> admins) {
    try (BufferedReader br = new BufferedReader(new FileReader("adminAccounts.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] info = line.split(",");
            admins.add(new Admin(info[0], info[1]));
        }
    } catch (IOException e) {
        System.out.println("读取文件失败: " + e.getMessage());
    }
}

public void write(ArrayList<Admin> admins) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("adminAccounts.txt"))) {
        for (Admin admin : admins) {
            String line = admin.getName() + "," + admin.getPassword();
            bw.write(line);
            bw.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
