package org.skypro.skyshop.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Поисковый движок.
 * Я поменял внутреннее хранилище со списка на... список (он и раньше был списком).
 * Главное изменение — метод search теперь возвращает Map<String, Searchable>,
 * отсортированную по имени через TreeMap.
 */
public class SearchEngine {
    private final List<Searchable> items = new ArrayList<>();

    /**
     * Конструктор. Параметр capacity оставил для совместимости, он больше не нужен.
     */
    public SearchEngine(int capacity) {
        // ArrayList растёт сам, ёмкость не нужна
    }

    public void add(Searchable item) {
        items.add(item);
    }

    /**
     * Поиск по строке. Теперь возвращает Map<String, Searchable>, отсортированную по имени.
     * Использую TreeMap — он автоматически сортирует ключи по алфавиту.
     */
    public Map<String, Searchable> search(String query) {
        Map<String, Searchable> results = new TreeMap<>();

        for (Searchable item : items) {
            String searchTerm = item.getSearchTerm();
            if (searchTerm != null && searchTerm.contains(query)) {
                results.put(item.getName(), item);
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

        for (Searchable item : items) {
            String searchTerm = item.getSearchTerm();
            if (searchTerm == null) {
                continue;
            }

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
     */
    private int countOccurrences(String str, String sub) {
        if (sub.isEmpty() || str.length() < sub.length()) {
            return 0;
        }

        int count = 0;
        int index = 0;

        while ((index = str.indexOf(sub, index)) != -1) {
            count++;
            index += sub.length();
        }

        return count;
    }
}