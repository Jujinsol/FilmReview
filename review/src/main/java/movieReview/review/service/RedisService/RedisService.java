package movieReview.review.service.RedisService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisService {
    private final StringRedisTemplate redisTemplate;

    public List<String> ReturnAllNewMovieList(){
        ListOperations<String, String> stringStringListOperations = redisTemplate.opsForList();
        Long newMovieName = stringStringListOperations.size("newMovieName");
        log.info("redis's size={}",newMovieName);
        return stringStringListOperations.range("newMovieName",0,newMovieName-1);
    }
}
