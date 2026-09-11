package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

import java.util.Objects;

/**
 * Абстрактный класс товара.
 * Теперь он реализует Searchable и содержит валидацию названия.
 * Все товары наследуют Product, поэтому проверка названия будет общей.
 *
 * Я добавил equals и hashCode по имени — продуктов с одинаковым именем быть не должно.
 * Это нужно, чтобы HashSet в SearchEngine не пускал дубликаты.
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

    /**
     * Я добавил equals и hashCode, которые учитывают только имя.
     * Продуктовая команда решила, что товаров с одинаковым именем не должно быть,
     * поэтому сравниваю только по name. Остальные поля не трогаю.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}