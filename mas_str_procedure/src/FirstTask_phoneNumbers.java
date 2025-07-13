import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstTask_phoneNumbers {

    // 1
    public static boolean validateRawNumber(String rawNumber) {
        String regex = "^8\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(rawNumber);
        return matcher.matches();
    }

    // 2
    public static boolean validateFormattedNumber(String formattedNumber) {
        String regex = "^\\+7-\\d{3}-\\d{3}-\\d{2}-\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(formattedNumber);
        return matcher.matches();
    }

    // 3
    public static String convertRawToFormatted(String rawNumber) {
        if (!validateRawNumber(rawNumber)) {
            return null;
        }
        char[] charArray = rawNumber.toCharArray();
        StringBuilder str = new StringBuilder();
        str.append("+7-");
        int index = 1;
        do {
            str.append(charArray[index]);
            index++;
            if (index == 4 || index == 7 || index == 9) {
                str.append("-");
            }
        } while (index < 11);
        return str.toString();
    }

    // 4
    public static String convertFormattedToRaw(String formattedNumber) {
        if (!validateFormattedNumber(formattedNumber)) {
            return null;
        }
        char[] charArray = formattedNumber.toCharArray();
        StringBuilder str = new StringBuilder();
        str.append("8");
        int index = 3;
        do {
            str.append(charArray[index]);
            index++;
            if (index == 6 || index == 10 || index == 13) {
                index++;
                continue;
            }
        } while (index < 16);
        return str.toString();
    }

    // 5
    public static String generateRawNumber(Random random) {
        StringBuilder str = new StringBuilder();
        str.append("89");
        int index = 0;
        do {
            str.append(random.nextInt(10));
            index++;
        } while (index < 9);
        return str.toString();
    }

    // 6
    public static String generateFormattedNumber(Random random) {
        StringBuilder str = new StringBuilder();
        str.append("+7-");
        int index = 0;
        do {
            str.append(random.nextInt(10));
            index++;
            if (index == 3 || index == 6 || index == 8) {
                str.append("-");
            }
        } while (index < 10);
        return str.toString();
    }

    // 7
    public static String[][] generateNumbers(int count, Random random) {
        int kol = 0;
        String number;
        Random rand = new Random();
        String [][] matrix = new String[2][count];
        do {
            number = generateRawNumber(rand);
            matrix[0][kol] = number;
            matrix[1][kol] = convertRawToFormatted(number);
            kol++;
        } while (kol < count);
        return matrix;
    }
}
