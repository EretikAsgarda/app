package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Корзина товаров.
 * Я заменил массив на ArrayList — теперь корзина не фиксированного размера.
 * Это удобнее: не нужно следить за вместимостью и выводить сообщение «Невозможно добавить».
 */
public class ProductBasket {
    // Я поменял массив на список. ArrayList подходит: мы только добавляем и удаляем,
    // обращения по индексу нет. Размер растёт автоматически.
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
        // Раньше тут был код с проверкой вместимости и выводом «Невозможно добавить продукт».
        // Теперь список не ограничен, поэтому проверка не нужна — просто удалил.
    }

    public int getTotalPrice() {
        int total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public int getSpecialCount() {
        int count = 0;
        for (Product product : products) {
            if (product.isSpecial()) {
                count++;
            }
        }
        return count;
    }

    public void printContents() {
        // Проверяю isEmpty() у списка — проще и понятнее, чем флаг isEmpty
        if (products.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }

        for (Product product : products) {
            System.out.println(product.toString());
        }

        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + getSpecialCount());
    }

    public boolean containsProduct(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Я добавил метод removeProductByName: удаляет все товары с указанным именем.
     * Возвращает список удалённых товаров. Если ничего не найдено — список пустой.
     */
    public List<Product> removeProductByName(String name) {
        List<Product> removed = new ArrayList<>();

        // Перебираю корзину и собираю товары с совпадающим именем
        for (Product product : products) {
            if (product.getName().equals(name)) {
                removed.add(product);
            }
        }

        // Удаляю все найденные товары из корзины
        products.removeAll(removed);

        return removed;
    }

    public void clear() {
        products.clear();
    }
}