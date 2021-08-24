package movieReview.review.controller.MainPage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/MainPage")
@Slf4j
@RequiredArgsConstructor
public class MainPageController {

    @GetMapping
    public String goMainPage(){
        return "MainPage/MainPage";
    }
}
