package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;


public class App {
    public static void main(String[] args) {
        Product bread = new SimpleProduct("Хлеб", 50);
        Product milk = new SimpleProduct("Молоко", 80);

        Product teaWithDiscount = new DiscountedProduct("Чай со скидкой", 200, 20);
        Product fixItem = new FixPriceProduct("Фикс-товар");

        ProductBasket basket = new ProductBasket();

        basket.addProduct(bread);
        basket.addProduct(milk);
        basket.addProduct(teaWithDiscount);
        basket.addProduct(fixItem);
        basket.addProduct(new SimpleProduct("Яйца", 120));

        basket.addProduct(new SimpleProduct("Сахар", 70)); // не влезет

        System.out.println("----------------- Содержимое корзины -----------------");
        basket.printContents();

        System.out.println("Общая стоимость: " + basket.getTotalPrice());

        System.out.println("Есть ли «Чай со скидкой» в корзине? " + basket.containsProduct("Чай со скидкой"));
        System.out.println("Есть ли «Сахар» в корзине? " + basket.containsProduct("Сахар"));

        basket.clear();

        System.out.println("----------------- Пустая корзина -----------------");
        basket.printContents();
        System.out.println("Общая стоимость пустой корзины: " + basket.getTotalPrice());
    }
}