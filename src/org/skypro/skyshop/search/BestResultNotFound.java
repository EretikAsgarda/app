package org.skypro.skyshop.search;

/**
 * Проверяемое исключение: выбрасывается, когда не найден ни один подходящий элемент
 * для метода findBestMatch. В сообщении указывается, для какого запроса ничего не нашлось.
 */
public class BestResultNotFound extends Exception {
    public BestResultNotFound(String query) {
        super("Не удалось найти наиболее подходящий элемент для запроса: \"" + query + "\"");
    }
}