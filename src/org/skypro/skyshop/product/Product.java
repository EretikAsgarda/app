package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

/**
 * Абстрактный класс товара.
 * Теперь он реализует Searchable и содержит валидацию названия.
 * Все товары наследуют Product, поэтому проверка названия будет общей.
 */
public abstract class Product implements Searchable {
    private final String name;

    public Product(String name) {
        // Проверка на null
        if (name == null) {
            throw new IllegalArgumentException("Название товара не может быть null");
        }
        // Проверка на пустую строку или строку из одних пробелов
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Название товара не может состоять только из пробелов или быть пустым"
            );
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    @Override
    public String getSearchTerm() {
        // Для поиска используем название товара
        return name;
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }
}