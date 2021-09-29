package movieReview.review.controller.MyPage.DeleteMovie;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.FileInfo.photoUriInfo;
import movieReview.review.Domain.MovieInfo.movieInfo;
import movieReview.review.service.Upload.UploadService;
import org.springframework.stereotype.Controller;
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
    @ResponseBody
    public Map<String, Object> DeleteCheckFunc(@RequestParam Map<String, Object> movies){
        log.info("첫번째 삭제확인영화 ={}, 두번째 삭제확인영화={}",movies.get("0"),movies.get("1"));
        // 여기서 DeletePage.html view를 반환시킬수있으면 참좋을탠데 이거왜안대지?
        return movies;
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
