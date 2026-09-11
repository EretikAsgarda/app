package org.skypro.skyshop.product;

/**
 * Товар со скидкой.
 * Добавлены проверки: базовая цена > 0 и процент скидки в диапазоне [0, 100].
 */
public class DiscountedProduct extends Product {
    private final int basePrice;
    private final int discountPercent;

    public DiscountedProduct(String name, int basePrice, int discountPercent) {
        super(name); // Проверка названия

        if (basePrice <= 0) {
            throw new IllegalArgumentException(
                    "Базовая цена должна быть строго больше 0, передано: " + basePrice
            );
        }

        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException(
                    "Процент скидки должен быть в диапазоне от 0 до 100 включительно, передано: " + discountPercent
            );
        }

        this.basePrice = basePrice;
        this.discountPercent = discountPercent;
    }

    @Override
    public int getPrice() {
        int discountAmount = basePrice * discountPercent / 100;
        return basePrice - discountAmount;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " (" + getDiscountPercent() + "%)";
    }
}