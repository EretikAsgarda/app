package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Article;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.search.BestResultNotFound;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // --- 1. Корзина и товары ---
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

        // Раньше тут был 6-й товар с комментарием «не влезет».
        // Я убрал его: корзина теперь на списке, ограничений нет.
        // Можно добавить сколько угодно — но по сценарию демо нам хватит пяти.
        // Я добавил ещё один товар с тем же именем, чтобы демо removeProductByName было нагляднее
        basket.addProduct(new SimpleProduct("Хлеб", 45));

        System.out.println("----------------- Содержимое корзины -----------------");
        basket.printContents();
        System.out.println("Общая стоимость: " + basket.getTotalPrice());
        System.out.println();


        // --- 2. Демонстрация removeProductByName ---
        System.out.println("--- Удаление существующего продукта ---");
        List<Product> removed = basket.removeProductByName("Хлеб");
        System.out.println("Удалённые продукты:");
        for (Product p : removed) {
            System.out.println("  " + p.toString());
        }

        System.out.println("\nКорзина после удаления «Хлеб»:");
        basket.printContents();
        System.out.println();

        System.out.println("--- Удаление несуществующего продукта ---");
        List<Product> removedNonExisting = basket.removeProductByName("Сахар");
        if (removedNonExisting.isEmpty()) {
            System.out.println("Список пуст");
        }
        System.out.println("\nКорзина после попытки удалить «Сахар»:");
        basket.printContents();
        System.out.println();


        // --- 3. Поисковый движок ---
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


        // --- 4. Демонстрация обычного поиска ---
        // Я поменял testSearch: теперь он принимает List, а не массив.
        testSearch(searchEngine, "Хлеб");
        testSearch(searchEngine, "чай");
        testSearch(searchEngine, "скидка");
        testSearch(searchEngine, "советы");
        testSearch(searchEngine, "несуществующий запрос");


        // --- 5. Демонстрация валидации (неправильные данные) ---
        System.out.println("--- Демонстрация валидации ---");
        try {
            SimpleProduct badBread = new SimpleProduct(null, 50);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение при создании товара: " + e.getMessage());
        }

        try {
            SimpleProduct badMilk = new SimpleProduct("   ", 80);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение при создании товара: " + e.getMessage());
        }

        try {
            SimpleProduct badEggs = new SimpleProduct("Яйца", 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение при создании товара: " + e.getMessage());
        }

        try {
            DiscountedProduct badTea = new DiscountedProduct("Плохой чай", -10, 20);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение при создании товара: " + e.getMessage());
        }

        try {
            DiscountedProduct badTea2 = new DiscountedProduct("Ещё плохой чай", 100, -5);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение при создании товара: " + e.getMessage());
        }

        try {
            DiscountedProduct badTea3 = new DiscountedProduct("И ещё плохой чай", 100, 150);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение при создании товара: " + e.getMessage());
        }


        // --- 6. Демонстрация findBestMatch (когда есть результат) ---
        System.out.println("\n--- Демонстрация findBestMatch (есть результат) ---");
        try {
            Searchable best = searchEngine.findBestMatch("чай");
            System.out.println("Лучший результат для \"чай\": " + best.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }


        // --- 7. Демонстрация findBestMatch (когда нет результата) ---
        System.out.println("\n--- Демонстрация findBestMatch (нет результата) ---");
        try {
            Searchable nonExisting = searchEngine.findBestMatch("несуществующий товар");
            System.out.println("Лучший результат: " + nonExisting.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Вспомогательный метод для вывода результатов поиска.
     * Я поменял тип результата с Searchable[] на List<Searchable>,
     * потому что search теперь возвращает список, а не массив.
     */
    private static void testSearch(SearchEngine engine, String query) {
        System.out.println("--- Поиск по запросу: \"" + query + "\" ---");
        List<Searchable> results = engine.search(query);

        if (results.isEmpty()) {
            System.out.println("Ничего не найдено");
        } else {
            for (Searchable item : results) {
                System.out.println(item.getStringRepresentation());
            }
        }
        System.out.println();
    }
}