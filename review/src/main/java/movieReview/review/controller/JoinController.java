package movieReview.review.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.userInfo;
import movieReview.review.repository.joinRepositoryImpl;
import movieReview.review.service.joinServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.exceptions.ParserInitializationException;

@Controller
@Slf4j
@RequiredArgsConstructor
public class JoinController {
    private final joinServiceImpl joinService;

    @GetMapping("/Join")
    public String joinPage(Model model){
        model.addAttribute("userInfo",new userInfo());
        return "Join/signUp";
    }

    @ResponseBody
    @PostMapping("/Join")
    public String joinUser(@ModelAttribute userInfo userinfo, RedirectAttributes redirectAttributes){
        int join = joinService.join(userinfo.getEmail(), userinfo.getId(), userinfo.getPassword(), null);
        String join2 = Integer.toString(join);
        return join2;
    }
}
