package org.top.springpenprobe;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import javax.imageio.IIOException;
import java.io.IOException;

@RestController
public class HttpParamsController {

    @GetMapping("request-example")
    public void requestExample(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println(request);
        System.out.println(response);

        String host = request.getRemoteHost();
        String method = request.getMethod();

        // можно записать в response всякое
        response.getWriter().println("host: " + host + "; method: " + method);
    }

    // get-параметры
    // GET // http://localhost:8000/get-params?a=20&b=hello
    @GetMapping("get-params")
    // можно указать: @Nullable @RequestParam Integer a - если могут прилететь незаполненные данные
    public String getParams(@RequestParam Integer a, @RequestParam String b) {
        return "[get-params]: received a = " + a + "; b = " + b + ";";
    }

    // post-параметры // также будет работать и с get-параметрами
    // POST // http://localhost:8000/post-params
    @PostMapping("post-params")
    public String postParams(@RequestParam Integer a, @RequestParam String b) {
        return "[post-params]: received a = " + a + "; b = " + b + ";";
    }

    // переменная пути // path-param // исп-ся с get, post и др. методами запроса
    // GET // http://localhost:8000/path-variable/111
    @GetMapping("path-variable/{id}")
    public String pathVariable(@PathVariable Integer id) {
        return "[path-variable]: id = " + id + ";";
    }

    // заголовки // могут передаваться в любом методе
    // обычно в заголовках передают не пользовательскую информацию, а техническую (куки)
    // GET // http://localhost:8000/header
    @GetMapping("header")
    public String header(@RequestHeader("X-My-Header") String myHeader) {
        return "[header]: myHeader = " + myHeader + ";";
    }

    // тело запроса
    // POST // http://localhost:8000/body
    @PostMapping("body")
    public String body(@RequestBody String content) throws InterruptedException {
        return "[body]: content = " + content + ";";
    }
}
