package org.skypro.skyshop.search;

import org.skypro.skyshop.product.Article;
import org.skypro.skyshop.product.Product;

/**
 * Поисковый движок. Хранит массив Searchable и умеет искать по нему.
 * По заданию: размер массива передаётся через конструктор, добавляем элементы через add().
 */
public class SearchEngine {
    private final Searchable[] items;
    private int size = 0; // Текущее количество реально добавленных элементов

    /**
     * Конструктор принимает ёмкость массива.
     * Я делаю массив фиксированного размера, как в корзине (ProductBasket).
     */
    public SearchEngine(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Ёмкость должна быть больше 0");
        }
        this.items = new Searchable[capacity];
    }

    /**
     * Добавить объект в поисковый индекс.
     * Если место закончилось — просто не добавляем и печатаю предупреждение.
     * Динамическое расширение массива по заданию не требуется.
     */
    public void add(Searchable item) {
        if (size >= items.length) {
            System.out.println("Не удалось добавить элемент: индекс заполнен.");
            return;
        }
        items[size] = item;
        size++;
    }

    /**
     * Поиск по строке. Возвращает до 5 совпадений.
     * Логика: перебираю все элементы до size, беру у каждого getSearchTerm(),
     * проверяю contains. Как только набираю 5 результатов — делаю break.
     * Массив результатов может содержать null — это допустимо по заданию.
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
            // Проверка на null нужна на всякий случай, хотя по логике не должна возникать
            if (searchTerm != null && searchTerm.contains(query)) {
                results[count] = item;
                count++;
                // По заданию: если нашли 5 результатов, дальше искать не нужно
                if (count == 5) {
                    break;
                }
            }
        }

        return results;
    }
}