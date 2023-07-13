package org.example;

public class QueryParams {
    private String name;
    private String manu;
    private double retPrice;
    // getter 和 setter 方法

    public boolean hasName() {
        return name != null;
    }

    public boolean hasManu() {
        return manu != null;
    }

    public boolean hasRetPrice() {
        return retPrice != 0.0;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setManu(String manu){
        this.manu = manu;
    }

    public void setRetPrice(double price){
        this.retPrice = price;
    }

    public String getName(){
        return name;
    }

    public String getManu(){
        return manu;
    }

    public double getRetPrice(){
        return retPrice;
    }
}

