
package org.skypro.skyshop.product;

public abstract class Product {
    private final String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int getPrice();

    // Сделал isSpecial абстрактным — теперь каждый наследник сам решает,
    // является ли он специальным товаром. Раньше тут был return false,
    // но по заданию метод должен быть абстрактным.
    public abstract boolean isSpecial();
}