package movieReview.review.controller.managerUpload;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.movieInfo;
import movieReview.review.dto.photoUriInfo;
import movieReview.review.service.Upload.UploadServiceImpl;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

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
    public String uploadCompletion(movieInfo movieinfo,MultipartFile moviePoster, Model model,RedirectAttributes redirectAttributes) throws IOException {
        moviePoster = movieinfo.getMoviePoster();
        files= new File(environment.getProperty("FilePath")+moviePoster.getOriginalFilename());

        // DB에 저장될 파일 경로
        String path = files.getPath();
        moviePoster.transferTo(files);

        photoUri.setPhotoOriName(moviePoster.getOriginalFilename());
        photoUri.setPhotoUri(path);

        // db 저장
        uploadService.create(photoUri);

        photoUriInfo photoUriInfo = uploadService.showPhoto(photoUri);
        ClassPathResource resource = new ClassPathResource("/moviePhoto/"+photoUriInfo.getPhotoOriName());

        Path path1 = Paths.get(resource.getPath());

        model.addAttribute("photoUriInfo",path1);
        return "/MyPage/managerUploadSuccess";
    }

}
