package org.skypro.skyshop.product;

public class SimpleProduct extends Product {
    private final int price;

    public SimpleProduct(String name, int price) {
        super(name);
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    // Так как isSpecial теперь абстрактный в Product,
    // я обязан переопределить его здесь. Обычный товар — не специальный.
    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice();
    }
}