package movieReview.review.controller.managerUpload;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Session.SessionConst;
import movieReview.review.Domain.MovieInfo.movieInfo;
import movieReview.review.service.Login.LoginService;
import movieReview.review.service.Upload.UploadService;
import movieReview.review.service.Upload.UploadServiceImpl;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.UUID;

import static org.apache.commons.io.FilenameUtils.getFullPath;

@Controller
@RequestMapping("/Upload")
@Slf4j
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class UploadController {
    private final LoginService loginServiceImpl;
    private final UploadService uploadService;


    @GetMapping
    public String uploadPage(
            @SessionAttribute(value = SessionConst.LoginId, required = false) String id,
            Model model){
        if (loginServiceImpl.FirstCheck(id)==1){
            log.info("사용자가 업로드에 접근");
            return "redirect:/";
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
        String storeFileName = uploadService.createStoreFileName(moviePoster.getOriginalFilename());

        // 경로에 파일 저장
        moviePoster.transferTo(new File(uploadService.getFullPath(storeFileName)));

        movieinfo.setPhotoOriName(storeFileName); //UUID로 변환된 사진파일이름 DB에 저장됨
        movieinfo.setPhotoUri(uploadService.getFullPath(moviePoster.getOriginalFilename()));

        // db 저장
        uploadService.create(movieinfo);
        return "redirect:/Upload/managerUploadSuccess";
    }

    @GetMapping("/managerUploadSuccess")
    public String SuccessPage(){
        return "/MyPage/managerUploadSuccess";
    }


}
