package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Article;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.search.BestResultNotFound;

public class App {
    public static void main(String[] args) {
        // --- 1. Корзина и товары (как раньше) ---
        ProductBasket basket = new ProductBasket();

        SimpleProduct bread = new SimpleProduct("Хлеб", 50);
        SimpleProduct milk = new SimpleProduct("Молоко", 80);
        DiscountedProduct teaWithDiscount = new DiscountedProduct("Чай со скидкой", 200, 20);
        FixPriceProduct fixItem = new FixPriceProduct("Фикс-товар");
        SimpleProduct eggs = new SimpleProduct("Яйца", 120);

        basket.addProduct(bread);
        basket.addProduct(milk);
        basket.addProduct(teaWithDiscount);
        basket.addProduct(fixItem);
        basket.addProduct(eggs);
        basket.addProduct(new SimpleProduct("Сахар", 70)); // не влезет

        System.out.println("----------------- Содержимое корзины -----------------");
        basket.printContents();
        System.out.println("Общая стоимость: " + basket.getTotalPrice());
        System.out.println();


        // --- 2. Поисковый движок ---
        SearchEngine searchEngine = new SearchEngine(20);

        searchEngine.add(bread);
        searchEngine.add(milk);
        searchEngine.add(teaWithDiscount);
        searchEngine.add(fixItem);
        searchEngine.add(eggs);

        Article articleBread = new Article(
                "Как выбрать хлеб",
                "Хлеб — один из самых популярных продуктов. При выборе обращайте внимание на состав и свежесть."
        );
        Article articleTea = new Article(
                "Всё о чае",
                "Чай бывает чёрным, зелёным, белым. Чай со скидкой — отличный повод попробовать новый сорт."
        );
        Article articleGeneral = new Article(
                "Советы по покупкам",
                "Планируйте покупки заранее, используйте скидки и фиксированные цены для экономии."
        );

        searchEngine.add(articleBread);
        searchEngine.add(articleTea);
        searchEngine.add(articleGeneral);


        // --- 3. Демонстрация обычного поиска ---
        testSearch(searchEngine, "Хлеб");
        testSearch(searchEngine, "чай");
        testSearch(searchEngine, "скидка");
        testSearch(searchEngine, "советы");
        testSearch(searchEngine, "несуществующий запрос");


        // --- 4. Демонстрация валидации (неправильные данные) ---
        System.out.println("--- Демонстрация валидации ---");
        try {
            SimpleProduct badBread = new SimpleProduct(null, 50); // null-название
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение при создании товара: " + e.getMessage());
        }

        try {
            SimpleProduct badMilk = new SimpleProduct("   ", 80); // название только из пробелов
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение при создании товара: " + e.getMessage());
        }

        try {
            SimpleProduct badEggs = new SimpleProduct("Яйца", 0); // цена 0
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение при создании товара: " + e.getMessage());
        }

        try {
            DiscountedProduct badTea = new DiscountedProduct("Плохой чай", -10, 20); // отрицательная цена
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение при создании товара: " + e.getMessage());
        }

        try {
            DiscountedProduct badTea2 = new DiscountedProduct("Ещё плохой чай", 100, -5); // скидка -5%
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение при создании товара: " + e.getMessage());
        }

        try {
            DiscountedProduct badTea3 = new DiscountedProduct("И ещё плохой чай", 100, 150); // скидка 150%
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение при создании товара: " + e.getMessage());
        }


        // --- 5. Демонстрация findBestMatch (когда есть результат) ---
        System.out.println("\n--- Демонстрация findBestMatch (есть результат) ---");
        try {
            Searchable best = searchEngine.findBestMatch("чай");
            System.out.println("Лучший результат для \"чай\": " + best.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }


        // --- 6. Демонстрация findBestMatch (когда нет результата) ---
        System.out.println("\n--- Демонстрация findBestMatch (нет результата) ---");
        try {
            Searchable nonExisting = searchEngine.findBestMatch("несуществующий товар");
            System.out.println("Лучший результат: " + nonExisting.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Вспомогательный метод, чтобы красиво вывести результаты обычного поиска.
     */
    private static void testSearch(SearchEngine engine, String query) {
        System.out.println("--- Поиск по запросу: \"" + query + "\" ---");
        Searchable[] results = engine.search(query);

        boolean hasResults = false;
        for (Searchable item : results) {
            if (item != null) {
                hasResults = true;
                System.out.println(item.getStringRepresentation());
            }
        }

        if (!hasResults) {
            System.out.println("Ничего не найдено");
        }
        System.out.println();
    }
}