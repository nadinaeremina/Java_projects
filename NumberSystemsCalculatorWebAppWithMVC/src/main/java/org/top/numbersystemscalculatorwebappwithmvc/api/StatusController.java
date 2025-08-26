package org.top.numbersystemscalculatorwebappwithmvc.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// любой контроллер помечается этой аннотацией
@RestController
// эта аннотация по умолчанию любой обьект будет сереализовать в json
// с помощью стандартного сериалайзера и записывать его в тело ответа
@RequestMapping("api")
public class StatusController {
    // если хотим вернуть ответ в виде json - то нужно ответ поместить в какой-нибудь обьект
    // заменим 'String' на 'StringMessage'
    @GetMapping
    public ApiMessages.StringMessage serverStatus() {
        return new ApiMessages.StringMessage("server is running");
    }

    @GetMapping("ping")
    public ApiMessages.StringMessage ping() {
        return new ApiMessages.StringMessage("ping");
    }
}
