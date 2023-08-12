package org.example.Admin;
import org.example.DatebaseInitializer;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.io.*;
public class AdminDatebaseInitializer implements DatebaseInitializer{

    private static final String fileName = "adminAccounts.xlsx";
    @Override
    public void initializeDatabase() {
        File file = new File(fileName);
        
        try {
            if (!file.exists()) {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("AdminAccounts");
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

    public void read(ArrayList<Admin> admins) {
        try (FileInputStream file = new FileInputStream(fileName)) {
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                String name = row.getCell(0).getStringCellValue();
                String password = row.getCell(1).getStringCellValue();
                admins.add(new Admin(name, password));
            }
            workbook.close();
        } catch (IOException e) {
            System.out.println("读取文件失败: " + e.getMessage());
        }
    }

    public void write(ArrayList<Admin> admins) {
        try (FileInputStream file = new FileInputStream(fileName);
            Workbook workbook = new XSSFWorkbook(file)) {
            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = 0;
            for (Admin admin : admins) {
                Row row = sheet.getRow(rowCount);
                if (row == null) {
                    row = sheet.createRow(rowCount);
                }
                Cell nameCell = row.createCell(0);
                nameCell.setCellValue(admin.getName());
                Cell passwordCell = row.createCell(1);
                passwordCell.setCellValue(admin.getPassword());
                rowCount++;
            }

            FileOutputStream outputStream = new FileOutputStream(fileName);
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
