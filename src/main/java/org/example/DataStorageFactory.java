package org.example;

import org.example.Admin.*;
import org.example.Product.*;
import org.example.User.*;

public class DataStorageFactory {
    public static DataStorage<Admin> createAdminDataStorage(String storageType) {
        if (storageType.equalsIgnoreCase("TextFile")) {
            return new AdminTextFileStorage();
        } else if (storageType.equalsIgnoreCase("Excel")) {
            return new AdminExcelStorage();
        } else if (storageType.equalsIgnoreCase("Database")) {
            return new AdminDatabaseStorage();
        }
        return null;
    }
    public static DataStorage<Product> createProductDataStorage(String storageType) {
        if (storageType.equalsIgnoreCase("TextFile")) {
            return new ProductTextFileStorage();
        } else if (storageType.equalsIgnoreCase("Excel")) {
            return new ProductExcelStorage();
        } else if (storageType.equalsIgnoreCase("Database")) {
            return new ProductDatabaseStorage();
        }
        return null;
    }
    public static UserStorageAdapter<User> createUserDataStorage(String storageType) {
        if (storageType.equalsIgnoreCase("TextFile")) {
            return new UserTextFileStorage();
        } else if (storageType.equalsIgnoreCase("Excel")) {
            return new UserExcelStorage();
        } else if (storageType.equalsIgnoreCase("Database")) {
            return new UserDatabaseStorage();
        }
        return null;
    }
}
