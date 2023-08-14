package org.example.User;

import org.example.DataStorage;

import java.util.ArrayList;

public interface UserStorageAdapter<T> extends DataStorage<T>{
    public void write(ArrayList<User> users, ArrayList<CartItem> cartItems, ArrayList<PurchaseItem> purchaseItems);
    public void read(ArrayList<User> users, ArrayList<CartItem> cartItems, ArrayList<PurchaseItem> purchaseItems);
}
