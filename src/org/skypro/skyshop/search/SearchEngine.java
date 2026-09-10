package org.skypro.skyshop.search;

import org.skypro.skyshop.product.Article;
import org.skypro.skyshop.product.Product;

/**
 * Поисковый движок.
 * Хранит массив Searchable, умеет искать до 5 совпадений (search)
 * и находить «самый подходящий» элемент (findBestMatch).
 */
public class SearchEngine {
    private final Searchable[] items;
    private int size = 0;

    public SearchEngine(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Ёмкость должна быть больше 0");
        }
        this.items = new Searchable[capacity];
    }

    public void add(Searchable item) {
        if (size >= items.length) {
            System.out.println("Не удалось добавить элемент: индекс заполнен.");
            return;
        }
        items[size] = item;
        size++;
    }

    /**
     * Обычный поиск: возвращает до 5 совпадений.
     */
    public Searchable[] search(String query) {
        Searchable[] results = new Searchable[5];
        int count = 0;

        for (int i = 0; i < size; i++) {
            Searchable item = items[i];
            if (item == null) {
                continue;
            }

            String searchTerm = item.getSearchTerm();
            if (searchTerm != null && searchTerm.contains(query)) {
                results[count] = item;
                count++;
                if (count == 5) {
                    break;
                }
            }
        }

        return results;
    }

    /**
     * Находит наиболее подходящий объект: тот, в котором подстрока query
     * встречается наибольшее количество раз в getSearchTerm().
     * Если совпадений нет — выбрасывает BestResultNotFound.
     */
    public Searchable findBestMatch(String query) throws BestResultNotFound {
        if (query == null || query.isEmpty()) {
            throw new BestResultNotFound(query);
        }

        Searchable bestMatch = null;
        int bestCount = -1;

        for (int i = 0; i < size; i++) {
            Searchable item = items[i];
            if (item == null) {
                continue;
            }

            String searchTerm = item.getSearchTerm();
            if (searchTerm == null) {
                continue;
            }

            // Считаем, сколько раз встречается подстрока query в searchTerm
            int count = countOccurrences(searchTerm, query);

            if (count > bestCount) {
                bestCount = count;
                bestMatch = item;
            }
        }

        if (bestMatch == null || bestCount == 0) {
            throw new BestResultNotFound(query);
        }

        return bestMatch;
    }

    /**
     * Вспомогательный метод: считает, сколько раз подстрока sub встречается в строке str.
     * Работает корректно даже при перекрывающихся вхождениях (хотя для простых запросов это не критично).
     */
    private int countOccurrences(String str, String sub) {
        if (sub.isEmpty() || str.length() < sub.length()) {
            return 0;
        }

        int count = 0;
        int index = 0;

        while ((index = str.indexOf(sub, index)) != -1) {
            count++;
            index += sub.length(); // Сдвигаем вперёд, чтобы не зациклиться
        }

        return count;
    }
}