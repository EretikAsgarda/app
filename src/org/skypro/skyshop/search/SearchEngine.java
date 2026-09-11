package org.skypro.skyshop.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Поисковый движок.
 * Я заменил массив на ArrayList — структура стала проще, не нужно следить за size.
 * Метод search теперь возвращает ВСЕ подходящие результаты в виде List,
 * а не только первые 5, как раньше.
 */
public class SearchEngine {
    // Я поменял массив на список. ArrayList — удобно добавлять, перебирать.
    private final List<Searchable> items = new ArrayList<>();

    /**
     * Конструктор. Раньше принимал ёмкость массива — теперь она не нужна,
     * но я оставил параметр, чтобы не ломать вызов в main. Просто игнорирую его.
     */
    public SearchEngine(int capacity) {
        // Ёмкость не нужна, ArrayList растёт сам.
        // Параметр оставил для совместимости с существующим кодом.
    }

    /**
     * Добавить объект в поисковый индекс.
     * Проверка на переполнение больше не нужна — список растёт автоматически.
     */
    public void add(Searchable item) {
        items.add(item);
    }

    /**
     * Поиск по строке. Возвращает ВСЕ совпадения, а не только 5, как раньше.
     * Логика та же: перебираю все элементы, беру getSearchTerm(), проверяю contains.
     */
    public List<Searchable> search(String query) {
        List<Searchable> results = new ArrayList<>();

        for (Searchable item : items) {
            String searchTerm = item.getSearchTerm();
            if (searchTerm != null && searchTerm.contains(query)) {
                results.add(item);
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