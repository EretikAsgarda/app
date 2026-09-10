package org.skypro.skyshop.product;

public class DiscountedProduct extends Product {
    private final int basePrice;
    private final int discountPercent;

    public DiscountedProduct(String name, int basePrice, int discountPercent) {
        super(name);
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

    // Товар со скидкой — специальный, возвращаю true.
    // Тут всё уже было правильно, ничего не менял.
    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " (" + getDiscountPercent() + "%)";
    }
}