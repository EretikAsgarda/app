package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Корзина товаров.
 * Я заменил список на Map: ключ — имя товара, значение — список товаров с этим именем.
 * Использую HashMap, потому что у нас есть несколько операций получения товаров по имени —
 * HashMap даёт быстрый доступ по ключу.
 */
public class ProductBasket {
    private final Map<String, List<Product>> products = new HashMap<>();

    public void addProduct(Product product) {
        // Получаю список товаров с таким именем. Если его нет — создаю новый.
        String name = product.getName();
        if (!products.containsKey(name)) {
            products.put(name, new ArrayList<>());
        }
        products.get(name).add(product);
    }

    public int getTotalPrice() {
        int total = 0;
        // Перебираю все значения мапы (каждый — список товаров), потом товары внутри списка
        for (List<Product> list : products.values()) {
            for (Product product : list) {
                total += product.getPrice();
            }
        }
        return total;
    }

    public int getSpecialCount() {
        int count = 0;
        for (List<Product> list : products.values()) {
            for (Product product : list) {
                if (product.isSpecial()) {
                    count++;
                }
            }
        }
        return count;
    }

    public void printContents() {
        if (products.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }

        for (List<Product> list : products.values()) {
            for (Product product : list) {
                System.out.println(product.toString());
            }
        }

        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + getSpecialCount());
    }

    public boolean containsProduct(String name) {
        // Теперь проверка — просто containsKey, без перебора. Быстро и просто.
        return products.containsKey(name);
    }

    /**
     * Удаляет все товары с указанным именем.
     * Теперь это просто remove по ключу — намного быстрее, чем перебирать список.
     */
    public List<Product> removeProductByName(String name) {
        List<Product> removed = products.remove(name);
        if (removed == null) {
            return new ArrayList<>();
        }
        return removed;
    }

    public void clear() {
        products.clear();
    }
}