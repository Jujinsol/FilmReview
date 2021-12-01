package movieReview.review.controller.WebSocketController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.WebSocket.Greeting;
import movieReview.review.Domain.WebSocket.HelloMessage;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

//
//@Slf4j
//@Controller
//@RequiredArgsConstructor
//public class WebSocketController {
//
//
//
//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Greeting greeting(HelloMessage message) throws Exception {
//        log.info("영화제목 ={} ",message.getName());
//        return new Greeting(HtmlUtils.htmlEscape(message.getName()));
//    }
//}
