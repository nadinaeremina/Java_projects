package org.top.homeworkhttprequests;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("tasks")
// tasks
public class TasksController {

    // POST // body
    // 1 вариант
    // http://localhost:80/tasks/pythagoras
    @PostMapping("pythagoras")
    public ResponseEntity<String> Pythagoras(@RequestBody Map<String, String> request) {
        try {
            double one = Double.parseDouble(request.get("a"));
            double two = Double.parseDouble(request.get("b"));
            double result = Math.sqrt(one*one+two*two);
            String resultStr = String.format("%.2f", result);
            return ResponseEntity.ok().body("Гипотенуза равна = " + resultStr);
        } catch (NumberFormatException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }
    // 2 вариант
    @PostMapping("hypotenuse")
    public String getHypotenuse(@RequestBody String request) {
        try {
            String[] tokens = request.split(" ");
            Double a = Double.parseDouble(tokens[0]);
            Double b = Double.parseDouble(tokens[1]);
            double c = Math.sqrt(a*a+b*b);
            String cStr = String.format("%.2f", c);
            return "hypotenuse is " + cStr;
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    // GET // Get-params
    // http://localhost:80/tasks/converter?value=20&inputType=b&outputType=kb
    @GetMapping("converter")
    public ResponseEntity<?> converter(@RequestParam Integer value, String inputType, String outputType) {
        float inputValue = 0;

        // проверяем входящий тип, инициализируем значение
        try{
            if (inputType.equalsIgnoreCase("b")) {
                inputValue = value;
            } else if (inputType.equalsIgnoreCase("kb")) {
                inputValue = value * 1024;
            } else if (inputType.equalsIgnoreCase("mb")) {
                inputValue = value * 1024 * 1024;
            } else if (inputType.equalsIgnoreCase("gb")) {
                inputValue = value * 1024 * 1024 * 1024;
            } else if (inputType.equalsIgnoreCase("tb")) {
                inputValue = value * 1024 * 1024 * 1024 * 1024;
            } else {
                throw new IllegalArgumentException("Ошибка ввода данных inputType: " + inputType);
            }
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }

        // получаем результат исходя из запрашиваемого типа
        try{
            if (outputType.equalsIgnoreCase("b")) {
                return ResponseEntity.ok(inputValue);
            } else if (outputType.equalsIgnoreCase("kb")) {
                return ResponseEntity.ok(inputValue / 1024);
            } else if (outputType.equalsIgnoreCase("mb")) {
                return ResponseEntity.ok(inputValue / (1024 * 1024));
            } else if (outputType.equalsIgnoreCase("gb")) {
                return ResponseEntity.ok(inputValue / (1024 * 1024 * 1024));
            } else if (outputType.equalsIgnoreCase("tb")) {
                return ResponseEntity.ok(inputValue / (1024 * 1024 * 1024 * 1024));
            } else {
                throw new IllegalArgumentException("Ошибка ввода данных outputType: " + outputType);
            }
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    // GET // HEADER
    // http://localhost:80/tasks/rle
    @GetMapping("rle")
    public ResponseEntity<String> compressString (@RequestHeader("inputString") String input) {

        if (!input.matches("[a-z]+")) {
            return ResponseEntity.badRequest().body("Строка должна состоять только из маленьких латинских букв!");
        }
        StringBuilder compressed = new StringBuilder();
        char currentChar = input.charAt(0);
        int count = 1;

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == currentChar) {
                count++;
            } else {
                appendCompressed(compressed, count, currentChar);
                currentChar = input.charAt(i);
                count = 1;
            }
        }
        appendCompressed(compressed, count, currentChar);
        return  ResponseEntity.ok("Полученная строка: " + compressed.toString());
    }

    private void appendCompressed(StringBuilder builder, int count, char character) {
        if (count > 1) {
            builder.append(count);
        }
        builder.append(character);
    }

    // POST // post-param
    @PostMapping("factorial")
    public  ResponseEntity<String> postParams(@RequestParam Integer n) {
        try {
            if (n < 0 || n > 20) {
                throw new IllegalArgumentException("Число должноьбыть от 0 до 20");
            }
            if (n == 0 || n == 1) {
                ResponseEntity.ok(1);
            }
            long result = 1;
            for (int i = 2; i <= n; i++) {
                result *= i;
            }
            return ResponseEntity.ok("Факториал " + n + "! = " + result);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Ошибка ввода данных!");
        }
    }

    // GET // url
    // http://localhost:80/tasks/url/5
    @GetMapping("url/{r}")
    public  ResponseEntity<String> pathUrl (@PathVariable Integer r) {
        try {
            double pi = 3.14;
            double l = 2 * pi * r;
            double s = pi * Math.pow(r, 2);
            return  ResponseEntity.ok("Длина = " + l + ", площадь = " + s);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Ошибка ввода данных!");
        }
    }
}
