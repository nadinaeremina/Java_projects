package org.top.strings;

// CustomString - класс кастомной строки, расширяющий возможности стандартной строки
public class CustomString {
    // поле - строковые данные
    protected String str;

    // конструкторы
    public CustomString() {
        str = "";
    }

    public CustomString(String str) {
        this.str = str;
    }

    // методы
    // getStrSymbol - получение символа по заданному индексу
    public String getStrSymbol(int index) {
        throw new UnsupportedOperationException("implement me!");
    }

    // insertStr - вставка строки toInsert начиная с позиции fromIndex
    public void insertStr(String toInsert, int fromIndex) {
        str = str.substring(0, fromIndex) + toInsert + str.substring(fromIndex);
    }

    @Override
    public String toString() {
        return str;
    }
}
