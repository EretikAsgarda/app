package org.skypro.skyshop.search;

/**
 * Интерфейс для объектов, по которым можно вести поиск.
 * Я вынес его в отдельный пакет search, чтобы не смешивать с продуктами и корзинами.
 */
public interface Searchable {

    // Термин для поиска: по чему именно мы ищем (имя товара, текст статьи и т.п.)
    String getSearchTerm();

    // Тип контента: PRODUCT, ARTICLE и т.д.
    String getContentType();

    // Имя объекта для отображения
    String getName();

    /**
     * Представление объекта в виде строки для результатов поиска.
     * По заданию этот метод не должен называться toString, потому что toString
     * уже используется для вывода товаров в корзине.
     * Сделал default-метод, чтобы не дублировать код в каждом классе.
     */
    default String getStringRepresentation() {
        return getName() + " — " + getContentType();
    }
}