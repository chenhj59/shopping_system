package org.example.Product;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.example.DataStorage;

import java.io.*;
import java.util.ArrayList;

public class ProductExcelStorage implements DataStorage<Product>{
    private static final String fileName = "productData.xlsx";

    public void initializeDatabase() {
        File file = new File(fileName);

        try {
            if (!file.exists()) {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("ProductData");
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

    public void read(ArrayList<Product> products) {
        try (FileInputStream file = new FileInputStream(fileName)) {
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                int productID = (int) row.getCell(0).getNumericCellValue();
                String productName = row.getCell(1).getStringCellValue();
                String productManufacturer = row.getCell(2).getStringCellValue();
                String productDateOfManufacture = row.getCell(3).getStringCellValue();
                String productModel = row.getCell(4).getStringCellValue();
                double productPurchasePrice = row.getCell(5).getNumericCellValue();
                double productRetailPrice = row.getCell(6).getNumericCellValue();
                int productInventory = (int) row.getCell(7).getNumericCellValue();
                products.add(new Product(productID, productName, productManufacturer, productDateOfManufacture, productModel, productPurchasePrice, productRetailPrice, productInventory));
            }
            workbook.close();
        } catch (IOException e) {
            System.out.println("读取文件失败: " + e.getMessage());
        }
    }

    public void write(ArrayList<Product> products) {
        try (FileInputStream file = new FileInputStream(fileName);
             Workbook workbook = new XSSFWorkbook(file)) {
            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = 0;
            for (Product product : products) {
                Row row = sheet.getRow(rowCount);
                if (row == null) {
                    row = sheet.createRow(rowCount);
                }
                Cell idCell = row.createCell(0);
                idCell.setCellValue(product.getID());
                Cell nameCell = row.createCell(1);
                nameCell.setCellValue(product.getName());
                Cell manufacturerCell = row.createCell(2);
                manufacturerCell.setCellValue(product.getManufacturer());
                Cell dateOfManufactureCell = row.createCell(3);
                dateOfManufactureCell.setCellValue(product.getDateOfManufacture());
                Cell modelCell = row.createCell(4);
                modelCell.setCellValue(product.getModel());
                Cell purcPriceCell = row.createCell(5);
                purcPriceCell.setCellValue(product.getPurcPrice());
                Cell retailPriceCell = row.createCell(6);
                retailPriceCell.setCellValue(product.getRetailPrice());
                Cell inventoryCell = row.createCell(7);
                inventoryCell.setCellValue(product.getInventory());
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
