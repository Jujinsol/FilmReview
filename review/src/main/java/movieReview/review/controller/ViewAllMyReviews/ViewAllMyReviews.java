package movieReview.review.controller.ViewAllMyReviews;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Session.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class ViewAllMyReviews {

    @GetMapping
    public void AllMyReviews(
            @SessionAttribute(value = SessionConst.LoginId) String id
            ){

    }
}
