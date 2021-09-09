package movieReview.review.controller.managerUpload;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Session.SessionConst;
import movieReview.review.dto.MovieInfo.movieInfo;
import movieReview.review.service.Login.LoginService;
import movieReview.review.service.Upload.UploadServiceImpl;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/Upload")
@Slf4j
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class UploadController {
    private File files;
    private final LoginService loginServiceImpl;
    private final Environment environment;
    private final UploadServiceImpl uploadService;

    @GetMapping
    public String uploadPage(@SessionAttribute(value = SessionConst.LoginId, required = false) String id,Model model){
        if (loginServiceImpl.FirstCheck(id)==1){
            log.info("사용자가 업로드에 접근");
            return "MainPage/MainPage";
        }

        model.addAttribute("movieInfo", new movieInfo());
        return "MyPage/managerUpload";
    }

    @PostMapping
    public String uploadCompletion(@Validated movieInfo movieinfo,
                                   BindingResult bindingResult,
                                   MultipartFile moviePoster)
                                   throws IOException {

        if(bindingResult.hasErrors() || moviePoster.isEmpty()){
            bindingResult.rejectValue("moviePoster","moviePosterEmpty");
            log.info("error={}",bindingResult);
            return "MyPage/managerUpload";
        }

        moviePoster = movieinfo.getMoviePoster();
        files= new File(environment.getProperty("FilePath")+moviePoster.getOriginalFilename());

        // DB에 저장될 파일 경로
        String path = files.getPath();
        moviePoster.transferTo(files);

        movieinfo.setPhotoOriName(moviePoster.getOriginalFilename());
        movieinfo.setPhotoUri(path);

        // db 저장
        uploadService.create(movieinfo);
        return "redirect:/Upload/managerUploadSuccess";
    }

    @GetMapping("/managerUploadSuccess")
    public String SuccessPage(){
        return "/MyPage/managerUploadSuccess";
    }

}
