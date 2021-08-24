package movieReview.review.controller.MyPage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.mangerInfo;
import movieReview.review.dto.userInfo;
import movieReview.review.service.Join.checkMangerOrUser;
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
    private final checkMangerOrUser checkMangerOrUser;

    @GetMapping("/{id}")
    public String userChagePage(@PathVariable("id") String id, Model model){
        int check = checkMangerOrUser.check(id);

        if(check==1){
            // 사용자
            userInfo findInfo = joinService.myInfo(id);

            model.addAttribute("userInfo",findInfo);

            model.addAttribute("id",findInfo.getId());
            model.addAttribute("password",findInfo.getPassword());
            return "/MyPage/userPwChange";

        }else{
            // 관리자
            mangerInfo mangerInfo = joinService.mangerInfo(id);;

            model.addAttribute("mangerInfo",mangerInfo);

            model.addAttribute("id",mangerInfo.getId());
            model.addAttribute("password",mangerInfo.getPassword());
            return "/MyPage/managerPwChange";
        }

    }

    @PostMapping("/{id}")
    public String userChangePassword(userInfo userinfo, @PathVariable("id") String id,RedirectAttributes redirectAttributes){
        int userUpdate = joinService.update(id, userinfo.getPassword());
        int mangerUpdate = joinService.mangerUpdate(id, userinfo.getPassword());
        if(userUpdate==1){
            redirectAttributes.addAttribute("id",id);
            return "redirect:/MyPage/change/{id}";
        }else if(mangerUpdate==1) {
            redirectAttributes.addAttribute("id",id);
            return "redirect:/MyPage/change/{id}";
        }else{
            return "/MainPage/MainPage";
        }
    }

    @GetMapping("/change/{id}")
    public String myInfo(@PathVariable("id") String id, Model model){
        int check = checkMangerOrUser.check(id);
        if(check==1){
            //사용자
            userInfo findResult = joinService.myInfo(id);
            model.addAttribute("user",findResult);
        }else{
            //관리자
            mangerInfo mangerInfo = joinService.mangerInfo(id);
            model.addAttribute("user",mangerInfo);
        }

        return "/MyPage/changeSuccess";
    }
}
