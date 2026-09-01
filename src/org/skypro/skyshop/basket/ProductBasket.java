package org.skypro.skyshop.basket;  // Адрес: папка basket внутри skyshop

import org.skypro.skyshop.product.Product;  // Чтобы знать, что такое Product

public class ProductBasket {
    // Корзина — это массив ровно на 5 товаров.
    // private — никто снаружи не может подменить этот массив.
    // final — сам массив нельзя заменить на другой, но внутри можно менять элементы.
    private final Product[] products = new Product[5];

    /**
     * Добавить товар в корзину.
     * Ищет первую пустую ячейку (null) и кладёт туда товар.
     * Если все ячейки заняты — пишет сообщение.
     */
    public void addProduct(Product product) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {      // Если ячейка пустая
                products[i] = product;      // Кладём товар
                return;                    // Выходим из метода — товар добавлен
            }
        }
        // Если цикл закончился и мы здесь — значит, свободных мест нет
        System.out.println("Невозможно добавить продукт");
    }

    /**
     * Посчитать общую стоимость всех товаров в корзине.
     * Проходим по массиву, если товар есть (не null) — прибавляем его цену.
     */
    public int getTotalPrice() {
        int total = 0;
        for (Product product : products) {
            if (product != null) {
                total += product.getPrice();
            }
        }
        return total;
    }

    /**
     * Напечатать содержимое корзины.
     * Для каждого товара печатаем «Название: цена».
     * В конце — «Итого: <сумма>». Если товаров нет — пишем «в корзине пусто».
     */
    public void printContents() {
        boolean isEmpty = true;              // Считаем, что корзина пустая, пока не найдём товар
        for (Product product : products) {
            if (product != null) {          // Если товар есть
                isEmpty = false;             // Значит, корзина не пустая
                System.out.println(product.getName() + ": " + product.getPrice());
            }
        }
        if (isEmpty) {
            System.out.println("в корзине пусто");
        } else {
            System.out.println("Итого: " + getTotalPrice());
        }
    }

    /**
     * Проверить, есть ли товар с таким именем в корзине.
     * Возвращает true, если нашли, иначе false.
     */
    public boolean containsProduct(String name) {
        for (Product product : products) {
            // Проверяем: товар есть (не null) и его имя совпадает с тем, что ищем
            if (product != null && product.getName().equals(name)) {
                return true;
            }
        }
        return false;                       // Если цикл закончился, а мы ничего не нашли
    }

    /**
     * Очистить корзину: сделать все ячейки пустыми (null).
     */
    public void clear() {
        for (int i = 0; i < products.length; i++) {
            products[i] = null;
        }
    }
}