package org.top.strings;

import java.util.regex.Pattern;

// CustomText - класс кастомного текста, наследник CustomString
public class CustomText extends CustomString {
    // поле - разделитель токенов текста
    protected String delimiter;

    // конструкторы
    public CustomText() {
        delimiter = "";
    }

    public CustomText(String content, String delimiter) {
        super(content);
        this.delimiter = delimiter;
    }

    // методы
    // getTextToken - получение токена (слова) текста по заданному индексу
    public String getTextToken(int index) {
        return str.split(Pattern.quote(delimiter))[index];
    }
}
