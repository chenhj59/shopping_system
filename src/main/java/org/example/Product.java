package org.example;

public class Product {
    private int productID;
    private String productName;
    private String productManufacturer;
    private String productDateOfManufacture;
    private String productModel;
    private double productPurchasePrice;
    private double productRetailPrice;
    private int productInventory;       // 库存

    public Product(){

    }

    public Product(int ID, String name, String manu, String date, String model, double purPrice, double retPrice, int inventory){
        this.productID = ID;
        this.productManufacturer = manu;
        this.productDateOfManufacture = date;
        this.productModel = model;
        this.productPurchasePrice = purPrice;
        this.productRetailPrice = retPrice;
        this.productInventory = inventory;
    }
    public int getID() {
        return productID;
    }

    public String getName() {
        return productName;
    }

    public String getManufacturer() {
        return productManufacturer;
    }

    public String getDateOfManufacture() {
        return productDateOfManufacture;
    }

    public String getModel(){
        return productModel;
    }

    public double getPurcPrice() {
        return productPurchasePrice;
    }

    public double getRetailPrice() {
        return productRetailPrice;
    }

    public int getInventory() {
        return productInventory;
    }

    public void setID(int ID) {
        this.productID = ID;
    }

    public void setName(String name) {
        this.productName = name;
    }

    public void setManufacturer(String manu) {
        this.productManufacturer = manu;
    }

    public void setDateOfManufacture(String date) {
        this.productDateOfManufacture = date;
    }

    public void setModel(String model){
        this.productModel = model;
    }

    public void setPurcPrice(double price) {
        this.productPurchasePrice = price;
    }

    public void setRetailPrice(double price) {
        this.productRetailPrice = price;
    }

    public void setInventory(int inventory) {
        this.productInventory = inventory;
    }
}
