package org.example;

import org.example.Product.Product;

public class CartItem extends Product{
    public int userID;
    public int quantity;

    public CartItem(){

    }
    public CartItem(int productID, int userID, String productName, double productPurchasePrice, int quantity){
        this.productID = productID;
        this.userID = userID;
        this.productName = productName;
        this.productPurchasePrice = productPurchasePrice;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUserID(){
        return this.userID;
    }
    
    public void setQunatity(int quantity)
    {
        this.quantity = quantity;
    }

    public void setUserID(int ID){
        this.userID = ID;
    }
}
