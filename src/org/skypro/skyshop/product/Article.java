package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

/**
 * Статья о товаре.
 * Добавлены проверки: заголовок и текст не могут быть null или состоять только из пробелов.
 */
public class Article implements Searchable {
    private final String title;
    private final String text;

    public Article(String title, String text) {
        if (title == null) {
            throw new IllegalArgumentException("Заголовок статьи не может быть null");
        }
        if (title.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Заголовок статьи не может состоять только из пробелов или быть пустым"
            );
        }

        if (text == null) {
            throw new IllegalArgumentException("Текст статьи не может быть null");
        }
        if (text.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Текст статьи не может состоять только из пробелов или быть пустым"
            );
        }

        this.title = title;
        this.text = text;
    }

    @Override
    public String getSearchTerm() {
        // Ищем и по заголовку, и по тексту
        return title + " " + text;
    }

    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public String toString() {
        return title + "\n" + text;
    }

    @Override
    public String getStringRepresentation() {
        return title + " (статья)";
    }
}