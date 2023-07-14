package org.example;

public class PurchaseItem extends CartItem{
    private String time;

    public PurchaseItem(){

    }

    public PurchaseItem(int productID, int userID, String productName, double productPurchasePrice, int quantity, String time){
        this.productID = productID;
        this.userID = userID;
        this.productName = productName;
        this.productPurchasePrice = productPurchasePrice;
        this.quantity = quantity;
        this.time = time;
    }

    public String getTime(){
        return this.time;
    }
    
    public void setTime(String time){
        this.time = time;
    }
}
