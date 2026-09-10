package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

/**
 * Абстрактный класс товара.
 * Теперь он реализует интерфейс Searchable, чтобы все товары сразу умели искаться.
 */
public abstract class Product implements Searchable {
    private final String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    /**
     * Реализация методов Searchable прямо в абстрактном классе.
     * Это удобно: все наследники (SimpleProduct, DiscountedProduct, FixPriceProduct)
     * автоматически получают эту функциональность, и мне не нужно править каждый из них.
     */

    @Override
    public String getSearchTerm() {
        // По заданию: для товара ищем по имени.
        return name;
    }

    @Override
    public String getContentType() {
        // Тип контента для товаров — PRODUCT
        return "PRODUCT";
    }

    // getName() уже есть, и он совпадает с getName() из интерфейса Searchable,
    // поэтому отдельно его переопределять не нужно.
}