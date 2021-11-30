package movieReview.review.controller.WebSocketController;

import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.WebSocket.Greeting;
import movieReview.review.Domain.WebSocket.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Slf4j
@Controller
public class WebSocketController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        log.info("영화제목 ={} ",message.getName());
        Thread.sleep(100); // simulated delay
        return new Greeting("추가된 영화제목 :  " + HtmlUtils.htmlEscape(message.getName()));
    }
}
