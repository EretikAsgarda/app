package org.skypro.skyshop.product;

/**
 * Простой товар с фиксированной ценой.
 * Добавлена проверка: цена должна быть строго больше 0.
 */
public class SimpleProduct extends Product {
    private final int price;

    public SimpleProduct(String name, int price) {
        super(name); // Здесь сработает проверка названия из Product

        if (price <= 0) {
            throw new IllegalArgumentException(
                    "Цена товара должна быть строго больше 0, передано: " + price
            );
        }

        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice();
    }
}