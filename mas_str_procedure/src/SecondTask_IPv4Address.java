import java.util.Random;

public class SecondTask_IPv4Address {

    // 1
    public static boolean validateIPv4(String ipv4String) {
        if (ipv4String == null || ipv4String.isEmpty()) {
            return false;
        }
        if (ipv4String.length() > 1) {
            int length = ipv4String.length();
            char[] buffer = new char[2];
            ipv4String.getChars(length - 2, length, buffer, 0);
            if (buffer[0] == '.' && buffer[1] == '.') {
                return false;
            }
        }
        String[] octets = ipv4String.split("\\.");
        // в регулярке точка = это любой символ - поэтому, чтобы нам здесь иметь ввиду точку - ее надо
        // экранировать, но чтобы это не воспринималось, как escape-последовательность - ставим еще один слэш
        // надо экранировать слэш
        if (octets.length != 4) {
            // у каждого октета-байта-длина должна быть не больше и не меньше 4
            return false;
        }
        try {
            for (String octet : octets) {
                int octetVal = Integer.parseInt(octet);
                if (octetVal < 0 || octetVal > 255) {
                    return false;
                }
                if (octet.length() > 1 && octet.startsWith("0")) {
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    // 2
    public static String generateRandomIPv4(Random random) {
        int kol = 0;
        int number;
        StringBuilder IPv4 = new StringBuilder();
        do {
            number = random.nextInt(256);
            IPv4.append(String.valueOf(number));
            kol++;
            if (kol == 4) {
                break;
            }
            IPv4.append(".");
        } while (true);
        return  IPv4.toString();
    }

    // 3
    public static String[] extractAllIPv4(String text) {
        // заменим все символы, не являющиеся цифрами или точками на один пробел
        String sanitizedText = text.replaceAll("[^0-9.]+", " ").trim();
        // [^0-9.]+ - все, что не цифра и не точка ('+' - в любом повторении)

        // сделаем сплит по пробелам
        String[] potentialAddress = sanitizedText.split(" ");

        // посчитаем кол-во валидных ip-адресов и запишем информацию о них
        boolean[] isValid = new boolean[potentialAddress.length];
        // создали массив буллов, в котором будем собирать информацию о том, валидный адрес или нет
        int validCount = 0;
        for (int i = 0; i < potentialAddress.length; i++) {
            if (validateIPv4(potentialAddress[i])) {
                isValid[i] = true;
                validCount++;
                // посчитаем, сколько валидных адресов
            } else {
                isValid[i] = false;
            }
        }

        // сформируем результат
        String[] address = new String[validCount];
        int n = 0;
        for (int i = 0; i < potentialAddress.length; i++) {
            if (isValid[i]) {
                address[n] = potentialAddress[i];
                // добавили наши валидные адреса в массив
                n++;
            }
        }

        // вернуть результат
        return address;
    }

    // 4
    public static String generateRandomText(int ipv4Count, double ipv4Percentage, Random random) {
        if (ipv4Count <= 0)
            return null;
        if (ipv4Percentage <= 0 || ipv4Percentage >= 1) {
            return  null;
        }
        String[] address = new String[ipv4Count];
        int n = 0;
        int kol = 0;
        for (int i = 0; i < ipv4Count; i++) {
            address[i] = generateRandomIPv4(random);
            kol += address[i].length();
        }
        double currentPercent = ipv4Percentage * 100;
        double necessaryPercent = 100 - currentPercent;
        double necessaryCount = kol * necessaryPercent / currentPercent;
        String text = "abcdefghijklmnopqrstuvwxyz0123456789 .,?!-;:";
        char[] charArray = text.toCharArray();
        double separator = necessaryCount / kol;
        int intSeparator = (int) separator;
        int elementarySeparator = 0, genNumber, lengthAddress = address.length,
                elementaryLength = 0, elementaryCount = 0;
        StringBuilder str = new StringBuilder();
        do {
            genNumber = random.nextInt(text.length());
            str.append(charArray[genNumber]);
            elementaryCount++;
            if (elementaryCount == necessaryCount) {
                break;
            }
            if (elementarySeparator < intSeparator) {
                elementarySeparator++;
            } else {
                if (elementaryLength != lengthAddress) {
                    str.append(" ");
                    elementaryCount++;
                    str.append(address[elementaryLength]);
                    if (elementaryCount == necessaryCount) {
                        break;
                    }
                    str.append(" ");
                    elementaryCount++;
                    if (elementaryCount == necessaryCount) {
                        break;
                    }
                    elementaryLength++;
                }
                elementarySeparator = 0;
            }
        } while (true);
        return str.toString();
    }
}

