package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;

public class App {
    public static void main(String[] args) {
        // Создаём продукты
        Product bread = new Product("Хлеб", 50);
        Product milk = new Product("Молоко", 80);
        Product eggs = new Product("Яйца", 120);
        Product tea = new Product("Чай", 200);
        Product coffee = new Product("Кофе", 350);
        Product sugar = new Product("Сахар", 70);

        // Создаём корзину
        ProductBasket basket = new ProductBasket();

        // 1. Добавление продукта в корзину
        basket.addProduct(bread);
        basket.addProduct(milk);
        basket.addProduct(eggs);
        basket.addProduct(tea);
        basket.addProduct(coffee);

        // 2. Добавление в заполненную корзину (нет места)
        basket.addProduct(sugar);

        // 3. Печать содержимого корзины с несколькими товарами
        System.out.println("\n--- Содержимое корзины ---");
        basket.printContents();

        // 4. Получение стоимости корзины с несколькими товарами
        System.out.println("\nОбщая стоимость: " + basket.getTotalPrice());

        // 5. Поиск товара, который есть в корзине
        System.out.println("\nЕсть ли «Чай» в корзине? " + basket.containsProduct("Чай"));

        // 6. Поиск товара, которого нет в корзине
        System.out.println("Есть ли «Сахар» в корзине? " + basket.containsProduct("Сахар"));

        // 7. Очистка корзины
        basket.clear();

        // 8. Печать содержимого пустой корзины
        System.out.println("\n--- Пустая корзина ---");
        basket.printContents();

        // 9. Получение стоимости пустой корзины
        System.out.println("\nОбщая стоимость пустой корзины: " + basket.getTotalPrice());

        // 10. Поиск товара по имени в пустой корзине
        System.out.println("\nЕсть ли «Хлеб» в пустой корзине? " + basket.containsProduct("Хлеб"));
    }
}