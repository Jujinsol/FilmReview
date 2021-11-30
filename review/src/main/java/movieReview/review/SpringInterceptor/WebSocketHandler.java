package movieReview.review.SpringInterceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.service.RedisService.RedisService;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler
{

    private final StringRedisTemplate redisTemplate;
    private final RedisService redisService;
    private HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); //웹소켓 세션을 담아둘 맵

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        sessionMap.put(session.getId(), session);
        log.info("연결완료 is Run");
        // 소켓 연결 완료시점에 실행하는 메서드
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 메시지 전송시점에 실행하는 메서드
        String payload = message.getPayload();
        log.info("새로운 영화 이름 ={}",payload);
        ListOperations<String, String> stringStringListOperations = redisTemplate.opsForList();
        stringStringListOperations.rightPush("newMovieName",payload);

        List<String> movieDatas = redisService.ReturnAllNewMovieList();

        for(String key : sessionMap.keySet()) {
            WebSocketSession wss = sessionMap.get(key);
            try {
                wss.sendMessage(new TextMessage(payload));
            }catch(Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("연결해제 is Run");
        sessionMap.remove(session.getId());
        super.afterConnectionClosed(session, status);
        // 소켓 연결 끊기는시점에 실행하는 메서드
    }


}
