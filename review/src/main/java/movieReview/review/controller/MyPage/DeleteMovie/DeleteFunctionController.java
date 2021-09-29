package movieReview.review.controller.MyPage.DeleteMovie;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.FileInfo.photoUriInfo;
import movieReview.review.Domain.MovieInfo.movieInfo;
import movieReview.review.service.Upload.UploadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/DeleteMovie")
public class DeleteFunctionController {

    private final UploadService uploadService;

    @GetMapping
    @ResponseBody
    public List<String> movieDelete(@RequestParam("deleteName") String movieName, RedirectAttributes redirectAttributes){
        return deletePhotoInfo(movieName, new movieInfo(), new ArrayList<>());
    }

    private List<String> deletePhotoInfo(String movieName,
                                         movieInfo movieinfo,
                                         List<String> photoOriNames) {
        movieinfo.setMovieName(movieName);
        List<photoUriInfo> moviePhotoOriName = uploadService.findMoviePhotoOriName(movieinfo);

        for (photoUriInfo photoUris : moviePhotoOriName) {
            log.info(photoUris.getPhotoOriName());
            photoOriNames.add(photoUris.getPhotoOriName());
        }

        return photoOriNames;
    }


}
