package movieReview.review.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.userInfo;
import movieReview.review.service.joinServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/MyPage")
@Slf4j
@RequiredArgsConstructor
public class MyPageController {
    private final joinServiceImpl joinService;

    @GetMapping("/{id}")
    public String userChagePage(@PathVariable("id") String id, Model model){
        System.out.println("userChangePage method run");
        userInfo findInfo = joinService.myInfo(id);

        model.addAttribute("userInfo",findInfo);

        model.addAttribute("id",findInfo.getId());
        model.addAttribute("password",findInfo.getPassword());
        return "/MyPage/userPwChange";
    }

}
