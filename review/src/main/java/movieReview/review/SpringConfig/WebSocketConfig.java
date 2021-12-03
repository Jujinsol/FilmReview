package movieReview.review.SpringConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.SpringInterceptor.WebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
@Slf4j
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 자바스크립트에     webSocket = new WebSocket("ws://localhost:8123/ConnectionUri");
        // 이부분에 들어가는곳에 uri주소랑 여기 uri주소랑 똑같아야함
        registry.addHandler(webSocketHandler,"/ConnectionUri");
    }
}
