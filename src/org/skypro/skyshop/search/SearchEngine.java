package org.skypro.skyshop.search;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Поисковый движок.
 * Я заменил List на HashSet — теперь дубликаты не добавляются.
 * Метод search возвращает TreeSet<Searchable> с сортировкой:
 * сначала самые длинные имена, при равенстве длины — натуральный порядок.
 */
public class SearchEngine {
    // Я поменял List на HashSet. Дубликаты больше не пройдут,
    // потому что в Product и Article реализованы equals и hashCode по имени.
    private final Set<Searchable> items = new HashSet<>();

    /**
     * Конструктор. Параметр capacity оставил для совместимости, он больше не нужен.
     */
    public SearchEngine(int capacity) {
        // HashSet растёт сам, ёмкость не нужна
    }

    public void add(Searchable item) {
        // HashSet автоматически отбросит дубликат, если equals и hashCode совпадают.
        items.add(item);
    }

    /**
     * Поиск по строке. Теперь возвращает Set<Searchable>, отсортированный по длине имени
     * (от самого длинного к самому короткому), при равенстве длины — натуральный порядок.
     *
     * Я использую TreeSet с компаратором из двух частей:
     * 1. Сравнение длины имён через Integer.compare (по убыванию — длинные первыми).
     * 2. Если длины равны — сравнение имён через compareTo (натуральный порядок).
     */
    public Set<Searchable> search(String query) {
        // Создаю TreeSet с компаратором
        Set<Searchable> results = new TreeSet<>(new Comparator<Searchable>() {
            @Override
            public int compare(Searchable a, Searchable b) {
                // Часть 1: сравниваю длины имён. b сначала — чтобы длинные были в начале.
                int lenCompare = Integer.compare(
                        b.getName().length(),
                        a.getName().length()
                );
                // Часть 2: если длины одинаковые — натуральный порядок по имени
                if (lenCompare != 0) {
                    return lenCompare;
                }
                return a.getName().compareTo(b.getName());
            }
        });

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