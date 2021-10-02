package movieReview.review.controller.managerUpload;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Session.SessionConst;
import movieReview.review.Domain.MovieInfo.movieInfo;
import movieReview.review.service.Login.LoginService;
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

@Controller
@RequestMapping("/Upload")
@Slf4j
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class UploadController {
    private final LoginService loginServiceImpl;
    private final UploadServiceImpl uploadService;

    @Value("${file.dir}")
    private String fileDir;

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
        String storeFileName = createStoreFileName(moviePoster.getOriginalFilename());

        // 경로에 파일 저장
        moviePoster.transferTo(new File(getFullPath(storeFileName)));

        movieinfo.setPhotoOriName(storeFileName); //UUID로 변환된 사진파일이름 DB에 저장됨
        movieinfo.setPhotoUri(fileDir+moviePoster.getOriginalFilename());

        // db 저장
        uploadService.create(movieinfo);
        return "redirect:/Upload/managerUploadSuccess";
    }

    @GetMapping("/managerUploadSuccess")
    public String SuccessPage(){
        return "/MyPage/managerUploadSuccess";
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext; // 서버에 저장할 파일명 생성
    }

    private String extractExt(String originalFileName){
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos+1); // .을 기준으로 파일확장자명만 반환
    }

    public String getFullPath(String filename){
        return fileDir+filename; // 파일 저장경로를 반환해준다. 파일저장폴더 + 원본파일명
    }
}
