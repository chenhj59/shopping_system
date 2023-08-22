package org.example.Product;

import org.example.QueryParams;

public class ProductQueryParams extends QueryParams{
    private String manu;
    private double retPriceLower;
    private double retPriceUpper;
    // getter 和 setter 方法

    public boolean hasName() {
        return name.equals("");
    }

    public boolean hasManu() {
        return manu.equals("");
    }

    public boolean hasRetPriceLower() {
        return retPriceLower == -1.0;
    }
    public boolean hasRetPriceUpper() {
        return retPriceUpper == -1.0;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setManu(String manu){
        this.manu = manu;
    }

    public void setRetPriceLower(double price){
        this.retPriceLower = price;
    }
    public void setRetPriceUpper(double price){
        this.retPriceUpper = price;
    }

    public String getName(){
        return name;
    }

    public String getManu(){
        return manu;
    }

    public double getRetPriceLower(){
        return retPriceLower;
    }
    public double getRetPriceUpper(){
        return retPriceUpper;
    }
}

