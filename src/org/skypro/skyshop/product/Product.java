package org.skypro.skyshop.product;  // Адрес: папка product внутри skyshop

public class Product {
    // Поля: название и цена. private — видно только внутри этого класса.
    // final — нельзя изменить после создания: товар создаётся с ценой, и она не меняется.
    private final String name;
    private final int price;

    // Конструктор: способ создать товар. Когда пишем new Product("Хлеб", 50),
    // сюда попадают "Хлеб" и 50, и они сохраняются в поля name и price.
    public Product(String name, int price) {
        this.name = name;      // this.name — поле класса, name — то, что передали в скобках
        this.price = price;
    }

    // Геттер (метод для чтения названия). Другие классы могут спросить getName(),
    // но не могут напрямую поменять name.
    public String getName() {
        return name;
    }

    // Геттер для цены
    public int getPrice() {
        return price;
    }
}