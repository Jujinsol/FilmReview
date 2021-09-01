package movieReview.review.controller.managerUpload;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.MovieInfo.movieInfo;
import movieReview.review.dto.FileInfo.photoUriInfo;
import movieReview.review.service.Upload.UploadServiceImpl;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public String uploadCompletion(@Validated movieInfo movieinfo, BindingResult bindingResult, MultipartFile moviePoster, Model model, RedirectAttributes redirectAttributes) throws IOException {
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

        photoUri.setPhotoOriName(moviePoster.getOriginalFilename());
        photoUri.setPhotoUri(path);

        // db 저장
        uploadService.create(photoUri);

        photoUriInfo photoUriInfo = uploadService.showPhoto(photoUri);
        ClassPathResource resource = new ClassPathResource("/moviePhoto/"+photoUriInfo.getPhotoOriName());

        Path path1 = Paths.get(resource.getPath());
        log.info("path1={}",path1);

        redirectAttributes.addAttribute("photoUriInfo",path1);
        return "redirect:MyPage/managerUploadSuccess";
    }

}
