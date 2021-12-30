package movieReview.review.controller.ViewAllMyReviews;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Session.SessionConst;
import movieReview.review.service.reviewFunction.GetAllMyReviews.ChangeOriginalMovieName;
import movieReview.review.service.reviewFunction.GetAllMyReviews.GetAllMyReviews;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("MyReview")
@RequiredArgsConstructor
@Slf4j
public class ViewAllMyReviews {
    private final ChangeOriginalMovieName changeOriginalMovieName;
    private final GetAllMyReviews getAllMyReviews;

    @GetMapping
    public String AllMyReviews(
            @SessionAttribute(value = SessionConst.LoginId) String id,
            Model model
            ) throws ClassNotFoundException {
        model.addAttribute("AllMyReviewInfo",getAllMyReviews.getAllMyReviews(id));
        model.addAttribute("OriMovieName",changeOriginalMovieName.ChangeMovieName(id));
        return "MyPage/MyReview/ViewAllReviews";
    }
}
