package org.example;

public class Product {
    private String name;
    private double price;
    private int inventory;
    private int quantitySold;
    private int quantity;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getInventory() {
        return inventory;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public void setInventory(int inventory)
    {
        this.inventory = inventory;
    }

    public void setQunatitySold(int quantitySold)
    {
        this.quantitySold = quantitySold;
    }

    public void setQunatity(int quantitySold)
    {
        this.quantity = quantity;
    }
}
