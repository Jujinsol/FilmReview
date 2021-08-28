package movieReview.review.controller.managerUpload;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.movieInfo;
import movieReview.review.dto.photoUriInfo;
import movieReview.review.service.Upload.UploadServiceImpl;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private final photoUriInfo photoUri;
    private final Environment environment;
    private final UploadServiceImpl uploadService;

    @GetMapping
    public String uploadPage(Model model){
        model.addAttribute("movieInfo", new movieInfo());
        return "MyPage/managerUpload";
    }

    @PostMapping
    public String uploadCompletion(movieInfo movieinfo) throws IOException {
        MultipartFile myPoster = movieinfo.getMoviePoster();
        files= new File(environment.getProperty("FilePath")+myPoster.getOriginalFilename());

        // DB에 저장될 파일 경로
        String path = files.getPath();
        myPoster.transferTo(files);

        photoUri.setPhotoOriName(myPoster.getOriginalFilename());
        photoUri.setPhotoUri(path);

        // db 저장
        int result = uploadService.create(photoUri);

        return "/MainPage/MainPage";
    }

}
