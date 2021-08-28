package movieReview.review.controller.managerUpload;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.movieInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public String uploadCompletion(movieInfo movieinfo) {
        MultipartFile file = movieinfo.getMoviePoster();
        log.info("upload File Name ={}",file.getOriginalFilename());
        log.info("uploda File Size={}",file.getSize());
        return file.getOriginalFilename();
    }
}
