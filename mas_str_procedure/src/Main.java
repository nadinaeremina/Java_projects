import java.util.Arrays;
import java.util.Random;

public class Main {
    // 1
    public static void testGenerateRandomIPv4() {
        Random rand = new Random();
        System.out.println(SecondTask_IPv4Address.generateRandomIPv4(rand));
    }

    // 2
    public static void testValidateIPv4() {
        System.out.println(SecondTask_IPv4Address.validateIPv4("127.0.0.1")); // true
        System.out.println(SecondTask_IPv4Address.validateIPv4(".")); // false
        System.out.println(SecondTask_IPv4Address.validateIPv4("1")); // false
        System.out.println(SecondTask_IPv4Address.validateIPv4("127.0.0.1..........")); // false
        System.out.println(SecondTask_IPv4Address.validateIPv4("0.0.0.0")); // true
        System.out.println(SecondTask_IPv4Address.validateIPv4("0.0.0.00")); // false
        System.out.println(SecondTask_IPv4Address.validateIPv4("Hello.World.World.Hello")); // false
    }

    // 3
    public static void testExtractAllIPv4() {
        String text = "Вчера я пытался подключиться к серверу с IP-адресом 192.168.1.1, " +
                "но у меня ничего не вышло. Мой друг посоветовал попробовать 256.300.400.500," +
                " но это тоже не сработало. Затем я нашел в интернете список адресов " +
                "и попробовал 10.0.0.1, который .15.оказался рабочим. " +
                "Еще один адрес, 172.16.254.1, тоже оказался валидным, " +
                "но 192.168.300.400 не работал.";
        String[] addresses = SecondTask_IPv4Address.extractAllIPv4(text);
        String[] addresses2 = SecondTask_IPv4Address.extractAllIPv4("6");
        String[] addresses3 = SecondTask_IPv4Address.extractAllIPv4("gh");
        System.out.println(Arrays.toString(addresses));
        System.out.println(Arrays.toString(addresses2)); // пусто
        System.out.println(Arrays.toString(addresses3)); // пусто
    }

    // 4
    public static void testGenerateRandomText() {
        Random rand = new Random();
        System.out.println(SecondTask_IPv4Address.generateRandomText(2, 0.5, rand));
        System.out.println(SecondTask_IPv4Address.generateRandomText(0, 0.5, rand)); // null
        System.out.println(SecondTask_IPv4Address.generateRandomText(1, 0.35, rand));
        System.out.println(SecondTask_IPv4Address.generateRandomText(3, 1, rand)); // null
    }

    // 1
    public static void testValidateRawNumber() {
        System.out.println(FirstTask_phoneNumbers.validateRawNumber("892215014523")); // false
        System.out.println(FirstTask_phoneNumbers.validateRawNumber("89221514523")); // true
        System.out.println(FirstTask_phoneNumbers.validateRawNumber("8j221514523")); // false
        System.out.println(FirstTask_phoneNumbers.validateRawNumber("99221514523")); // false
    }

    // 2
    public static void testValidateFormattedNumber() {
        System.out.println(FirstTask_phoneNumbers.validateFormattedNumber("892215014523")); // false
        System.out.println(FirstTask_phoneNumbers.validateFormattedNumber("89221514523")); // false
        System.out.println(FirstTask_phoneNumbers.validateFormattedNumber("8j221514523")); // false
        System.out.println(FirstTask_phoneNumbers.validateFormattedNumber("99221514523")); // false
        System.out.println(FirstTask_phoneNumbers.validateFormattedNumber("+79221514523")); // false
        System.out.println(FirstTask_phoneNumbers.validateFormattedNumber("+7-922-15-450-23")); // false
        System.out.println(FirstTask_phoneNumbers.validateFormattedNumber("+7-922-15450-23")); // false
        System.out.println(FirstTask_phoneNumbers.validateFormattedNumber("+7-922-151-45-23")); // true
    }

    // 3
    public static void testConvertRawToFormatted() {
        System.out.println(FirstTask_phoneNumbers.convertRawToFormatted("892215014523")); // null
        System.out.println(FirstTask_phoneNumbers.convertRawToFormatted("+7-922-151-45-23")); // null
        System.out.println(FirstTask_phoneNumbers.convertRawToFormatted("89221514523"));
    }

    // 4
    public static void  testConvertFormattedToRaw() {
        System.out.println(FirstTask_phoneNumbers.convertFormattedToRaw("89221514523")); // null
        System.out.println(FirstTask_phoneNumbers.convertFormattedToRaw("+7-922-15450-23")); // null
        System.out.println(FirstTask_phoneNumbers.convertFormattedToRaw("+7-922-151-45-23"));
    }

    // 5
    public static void testGenerateRawNumber() {
        Random rand = new Random();
        System.out.println(FirstTask_phoneNumbers.generateRawNumber(rand));
    }

    // 6
    public static void testGenerateFormattedNumber() {
        Random rand = new Random();
        System.out.println(FirstTask_phoneNumbers.generateFormattedNumber(rand));
    }

    // 7
    public static void testGenerateNumbers() {
        Random rand = new Random();
        String [][] matrix = FirstTask_phoneNumbers.generateNumbers(4, rand);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // 1
        // testGenerateRandomIPv4();
        // 2
        // testValidateIPv4();
        // 3
        // testExtractAllIPv4();
        // 4
        // testGenerateRandomText();

        // 1
        // testValidateRawNumber();
        // 2
        //testValidateFormattedNumber();
        // 3
        // testConvertRawToFormatted();
        // 4
        // testConvertFormattedToRaw();
        // 5
        // testGenerateRawNumber();
        // 6
        // testGenerateFormattedNumber();
        // 7
        // testGenerateNumbers();
    }
}