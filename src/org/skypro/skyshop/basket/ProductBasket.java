package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private final Product[] products = new Product[5];

    public void addProduct(Product product) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                products[i] = product;
                return;
            }
        }
        System.out.println("Невозможно добавить продукт");
    }

    public int getTotalPrice() {
        int total = 0;
        for (Product product : products) {
            if (product != null) {
                total += product.getPrice();
            }
        }
        return total;
    }

    // Вынес логику подсчёта специальных товаров в отдельный метод.
    // Раньше это считалось прямо внутри printContents —
    // теперь printContents только печатает, а считает этот метод.
    public int getSpecialCount() {
        int count = 0;
        for (Product product : products) {
            if (product != null && product.isSpecial()) {
                count++;
            }
        }
        return count;
    }

    public void printContents() {
        boolean isEmpty = true;

        for (Product product : products) {
            if (product != null) {
                isEmpty = false;
                System.out.println(product.toString());
            }
        }

        if (isEmpty) {
            System.out.println("в корзине пусто");
        } else {
            System.out.println("Итого: " + getTotalPrice());
            // Вместо переменной specialCount вызываю метод getSpecialCount()
            System.out.println("Специальных товаров: " + getSpecialCount());
        }
    }

    public boolean containsProduct(String name) {
        for (Product product : products) {
            if (product != null && product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < products.length; i++) {
            products[i] = null;
        }
    }
}