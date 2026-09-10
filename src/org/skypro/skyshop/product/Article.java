package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

/**
 * Статья о товаре. Немодифицируемая (immutable) сущность, как и товары.
 * Добавил реализацию Searchable, чтобы статьи можно было искать.
 */
public class Article implements Searchable {
    private final String title;
    private final String text;

    public Article(String title, String text) {
        this.title = title;
        this.text = text;
    }

    @Override
    public String getSearchTerm() {
        // По заданию: для статьи ищем и по названию, и по тексту.
        // Склеиваю title и text — так запрос найдёт статью, даже если слово есть только в тексте.
        return title + " " + text;
    }

    @Override
    public String getContentType() {
        // Тип контента для статей — ARTICLE
        return "ARTICLE";
    }

    @Override
    public String getName() {
        // Для отображения имени статьи достаточно её заголовка
        return title;
    }

    /**
     * toString для красивого вывода статьи (например, в админке или отчёте).
     * Формат строго по заданию:
     * Название статьи
     * Текст статьи
     */
    @Override
    public String toString() {
        return title + "\n" + text;
    }

    /**
     * Переопределяю getStringRepresentation, чтобы в результатах поиска
     * было более понятное представление, чем просто «Название — ARTICLE».
     * Так пользователю понятнее, что это статья.
     */
    @Override
    public String getStringRepresentation() {
        return title + " (статья)";
    }
}