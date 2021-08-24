package movieReview.review.controller.MyPage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.userInfo;
import movieReview.review.service.Join.joinServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/MyPage")
@Slf4j
@RequiredArgsConstructor
public class MyPageController {
    private final joinServiceImpl joinService;

    @GetMapping("/{id}")
    public String userChagePage(@PathVariable("id") String id, Model model){
        userInfo findInfo = joinService.myInfo(id);

        model.addAttribute("userInfo",findInfo);

        model.addAttribute("id",findInfo.getId());
        model.addAttribute("password",findInfo.getPassword());
        return "/MyPage/userPwChange";
    }

    @PostMapping("/{id}")
    public String userChangePassword(userInfo userinfo, @PathVariable("id") String id,RedirectAttributes redirectAttributes){
        int update = joinService.update(id, userinfo.getPassword());
        if(update==1){
            redirectAttributes.addAttribute("id",id);
            return "redirect:/MyPage/change/{id}";
        }else {
            return "/MainPage/MainPage";
        }
    }

    @GetMapping("/change/{id}")
    public String myInfo(@PathVariable("id") String id, Model model){
        userInfo findResult = joinService.myInfo(id);
        model.addAttribute("userInfo",findResult);
        return "/MyPage/changeSuccess";
    }
}
