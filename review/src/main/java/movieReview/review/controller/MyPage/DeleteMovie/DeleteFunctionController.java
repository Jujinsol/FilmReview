package movieReview.review.controller.MyPage.DeleteMovie;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.FileInfo.photoUriInfo;
import movieReview.review.Domain.MovieInfo.movieInfo;
import movieReview.review.service.Upload.UploadService;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/DeleteMovie")
public class DeleteFunctionController {

    private final UploadService uploadService;

    @GetMapping
    @ResponseBody
    public List<String> movieDelete(@RequestParam("deleteName") String movieName){
        return deletePhotoInfo(movieName, new movieInfo(), new ArrayList<>());
    }

    @GetMapping("/DeleteCheck")
    public String DeleteCheckFunc(@RequestParam Map<String, Object> movies,
                                  Model model){
        model.addAttribute("movies",movies);
        return "/MyPage/DeletePage";
    }

    @GetMapping("/DeleteFunc")
    @ResponseBody
    public int DeleteFunc(@RequestParam("deleteName") String DropOriName){
        movieInfo movieInfo = new movieInfo();
        movieInfo.setPhotoOriName(DropOriName);
        return uploadService.movieDelete(movieInfo);
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
