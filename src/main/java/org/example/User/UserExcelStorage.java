package org.example.User;
import java.util.ArrayList;

import org.example.DataStorage;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
public class UserExcelStorage implements UserStorageAdapter<User>{
    private static final String fileName = "userData.xlsx";
    @Override
    public void initializeDatabase() {
        File file = new File(fileName);

        try {
            if (!file.exists()) {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet userSheet = workbook.createSheet("usersAccount");
                XSSFSheet cartSheet = workbook.createSheet("usersShoppingCart");
                XSSFSheet purchaseSheet = workbook.createSheet("usersPurchaseRecord");
                FileOutputStream outputStream = new FileOutputStream(file);
                workbook.write(outputStream);
                outputStream.close();
                System.out.println("Excel 文件已创建: " + file.getName());
            } else {
                System.out.println("Excel 文件已存在: " + file.getName());
            }
        } catch (IOException e) {
            System.out.println("发生IO异常: " + e.getMessage());
        }
    }
    @Override
    public void read(ArrayList<User> users){

    }
    public void read(ArrayList<User> users, ArrayList<CartItem> cartItems, ArrayList<PurchaseItem> purchaseItems) {
        try (FileInputStream file = new FileInputStream(fileName)) {
            Workbook workbook = new XSSFWorkbook(file);
            // 读取用户账户数据
            Sheet userSheet = workbook.getSheetAt(0);
            for (Row row : userSheet) {
                int id = (int) row.getCell(0).getNumericCellValue();
                String username = row.getCell(1).getStringCellValue();
                String password = row.getCell(2).getStringCellValue();
                String userLevel = row.getCell(3).getStringCellValue();
                String registerTime = row.getCell(4).getStringCellValue();
                double totalCost = row.getCell(5).getNumericCellValue();
                String phoneNumber = row.getCell(6).getStringCellValue();
                String email = row.getCell(7).getStringCellValue();
                users.add(new User(id, username, password, userLevel, registerTime, totalCost, phoneNumber, email));
            }

            // 读取购物车数据
            Sheet cartSheet = workbook.getSheetAt(1);
            for (Row row : cartSheet) {
                int id = (int) row.getCell(0).getNumericCellValue();
                int userID = (int) row.getCell(1).getNumericCellValue();
                String name = row.getCell(2).getStringCellValue();
                double purcPrice = row.getCell(3).getNumericCellValue();
                int quantity = (int) row.getCell(4).getNumericCellValue();
                cartItems.add(new CartItem(id, userID, name, purcPrice, quantity));
            }

            // 读取购买记录数据
            Sheet purchaseSheet = workbook.getSheetAt(2);
            for (Row row : purchaseSheet) {
                int id = (int) row.getCell(0).getNumericCellValue();
                int userID = (int) row.getCell(1).getNumericCellValue();
                String name = row.getCell(2).getStringCellValue();
                double purcPrice = row.getCell(3).getNumericCellValue();
                int quantity = (int) row.getCell(4).getNumericCellValue();
                String time = row.getCell(5).getStringCellValue();
                purchaseItems.add(new PurchaseItem(id, userID, name, purcPrice, quantity, time));
            }
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void write(ArrayList<User> users){

    }
    public void write(ArrayList<User> users, ArrayList<CartItem> cartItems, ArrayList<PurchaseItem> purchaseItems){
        try (FileInputStream file = new FileInputStream(fileName);
            Workbook workbook = new XSSFWorkbook(file)) {
            Sheet userSheet = workbook.getSheetAt(0);
            
            int userRowNum = 0;
            for (User user : users) {
                Row row = userSheet.getRow(userRowNum);
                if (row == null) {
                    row = userSheet.createRow(userRowNum);
                }
                row.createCell(0).setCellValue(user.getID());
                row.createCell(1).setCellValue(user.getUsername());
                row.createCell(2).setCellValue(user.getPassword());
                row.createCell(3).setCellValue(user.getUserLevel());
                row.createCell(4).setCellValue(user.getRegisterTime());
                row.createCell(5).setCellValue(user.getTotalCost());
                row.createCell(6).setCellValue(user.getPhoneNumber());
                row.createCell(7).setCellValue(user.getEmail());
                userRowNum++;
            }
            
            Sheet cartSheet = workbook.getSheetAt(1);
            int cartRowNum = 0;
            for (CartItem cartItem : cartItems) {
                Row row = cartSheet.getRow(cartRowNum);
                if (row == null) {
                    row = cartSheet.createRow(cartRowNum);
                }
                row.createCell(0).setCellValue(cartItem.getID());
                row.createCell(1).setCellValue(cartItem.getUserID());
                row.createCell(2).setCellValue(cartItem.getName());
                row.createCell(3).setCellValue(cartItem.getPurcPrice());
                row.createCell(4).setCellValue(cartItem.getQuantity());
                cartRowNum++;
            }

            Sheet purchaseSheet = workbook.getSheetAt(2);
            int purchaseRowNum = 0;
            for (PurchaseItem purchaseItem : purchaseItems) {
                Row row = purchaseSheet.getRow(purchaseRowNum);
                if (row == null) {
                    row = purchaseSheet.createRow(purchaseRowNum);
                }
                row.createCell(0).setCellValue(purchaseItem.getID());
                row.createCell(1).setCellValue(purchaseItem.getUserID());
                row.createCell(2).setCellValue(purchaseItem.getName());
                row.createCell(3).setCellValue(purchaseItem.getPurcPrice());
                row.createCell(4).setCellValue(purchaseItem.getQuantity());
                row.createCell(5).setCellValue(purchaseItem.getTime());
                purchaseRowNum++;
            }
            
            FileOutputStream outputStream = new FileOutputStream(fileName);
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
