package org.skypro.skyshop;          // «Адрес» этого класса: папка org/skypro/skyshop
import org.skypro.skyshop.basket.ProductBasket;  // Подключаем класс корзины, чтобы можно было его использовать
import org.skypro.skyshop.product.Product;       // Подключаем класс товара

public class App {
    public static void main(String[] args) {
        // Создаём товары: название и цена
        Product bread = new Product("Хлеб", 50);      // Товар: Хлеб, 50 рублей
        Product milk = new Product("Молоко", 80);     // Товар: Молоко, 80 рублей
        Product eggs = new Product("Яйца", 120);     // Товар: Яйца, 120 рублей
        Product tea = new Product("Чай", 200);       // Товар: Чай, 200 рублей
        Product coffee = new Product("Кофе", 350);   // Товар: Кофе, 350 рублей
        Product sugar = new Product("Сахар", 70);     // Товар: Сахар, 70 рублей

        // Создаём одну корзину для покупок
        ProductBasket basket = new ProductBasket();  // Пустая корзина на 5 товаров

        // 1. Добавляем 5 товаров (ровно столько, сколько влезает)
        basket.addProduct(bread);
        basket.addProduct(milk);
        basket.addProduct(eggs);
        basket.addProduct(tea);
        basket.addProduct(coffee);

        // 2. Пытаемся добавить шестой товар — корзина уже полная
        basket.addProduct(sugar);                   // Будет выведено: «Невозможно добавить продукт»

        // 3. Печатаем содержимое корзины
        System.out.println("----------------- Содержимое корзины -----------------");
        basket.printContents();                     // Выведет все товары и «Итого: 800»

        // 4. Получаем общую стоимость товаров в корзине
        System.out.println("Общая стоимость: " + basket.getTotalPrice());

        // 5. Проверяем, есть ли «Чай» в корзине (должен быть)
        System.out.println("Есть ли «Чай» в корзине? " + basket.containsProduct("Чай"));

        // 6. Проверяем, есть ли «Сахар» в корзине (его нет, потому что не влез)
        System.out.println("Есть ли «Сахар» в корзине? " + basket.containsProduct("Сахар"));

        // 7. Очищаем корзину — делаем её пустой
        basket.clear();                             // Все ячейки корзины становятся null

        // 8. Печатаем пустую корзину
        System.out.println("----------------- Пустая корзина -----------------");
        basket.printContents();                     // Будет: «в корзине пусто»

        // 9. Стоимость пустой корзины
        System.out.println("Общая стоимость пустой корзины: " + basket.getTotalPrice());

        // 10. Ищем товар в пустой корзине
        System.out.println("Есть ли «Хлеб» в пустой корзине? " + basket.containsProduct("Хлеб"));
    }
}