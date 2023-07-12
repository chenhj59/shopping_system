package org.example;
import java.util.ArrayList;
import java.util.List;

public class UserShoppingAction {
    private List<Product> shoppingCart;

    public void shop() {
        Product product1 = new Product("Apple", 10.0);
        Product product2 = new Product("Banana", 5.0);

        shoppingCart = new ArrayList<>();
        shoppingCart.add(product1);
        shoppingCart.add(product2);

        double totalCost = 0.0;
        for (Product product : shoppingCart) {
            totalCost += product.getPrice();
        }

        System.out.println("Shopping cart total cost: " + totalCost);
    }
}
