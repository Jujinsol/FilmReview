package movieReview.review.controller.managerUpload;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.movieInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Upload")
@Slf4j
@RequiredArgsConstructor
public class UploadController {

    @GetMapping
    public String uploadPage(Model model){
        model.addAttribute("movieInfo", new movieInfo());
        return "MyPage/managerUpload";
    }

    @PostMapping
    public String uploadCompletion(movieInfo movieinfo){
        log.info("movieinfo.getMoviePoster={}",movieinfo.getMoviePoster());
        return "ok";
    }
}
