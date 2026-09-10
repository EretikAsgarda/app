package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Article;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

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
        // Ёмкость 20 — хватит и для товаров, и для статей.
        SearchEngine searchEngine = new SearchEngine(20);

        // Добавляю товары в поиск
        searchEngine.add(bread);
        searchEngine.add(milk);
        searchEngine.add(teaWithDiscount);
        searchEngine.add(fixItem);
        searchEngine.add(eggs);

        // Создаю несколько статей и тоже добавляю в поиск
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


        // --- 3. Демонстрация поиска ---
        testSearch(searchEngine, "Хлеб");
        testSearch(searchEngine, "чай");
        testSearch(searchEngine, "скидка");
        testSearch(searchEngine, "советы");
        testSearch(searchEngine, "несуществующий запрос");
    }

    /**
     * Вспомогательный метод, чтобы красиво вывести результаты поиска.
     * Я вынес это в отдельный метод, чтобы main оставался чистым.
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