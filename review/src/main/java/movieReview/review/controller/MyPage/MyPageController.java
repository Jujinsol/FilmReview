package movieReview.review.controller.MyPage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Session.SessionConst;
import movieReview.review.dto.mangerInfo;
import movieReview.review.dto.updatePageDto.updateDto;
import movieReview.review.dto.userInfo;
import movieReview.review.service.Join.checkMangerOrUser;
import movieReview.review.service.Join.joinService;
import movieReview.review.service.Join.joinServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/MyPage")
@Slf4j
@RequiredArgsConstructor
public class MyPageController {
    private final joinService joinService;
    private final checkMangerOrUser checkMangerOrUser;

    @GetMapping
    public String userChagePage(@SessionAttribute(name = SessionConst.LoginId, required = false) String id, Model model) {
        if (checkMangerOrUser.check(id) == 1) {
            // 사용자
            userInfo findInfo = joinService.myInfo(id);

            model.addAttribute("id", findInfo.getId());
            model.addAttribute("password", findInfo.getPassword());
            model.addAttribute("userInfo", findInfo);

            return "/MyPage/userPwChange";

        }

        // 관리자
        mangerInfo mangerInfo = joinService.mangerInfo(id);

        model.addAttribute("id", mangerInfo.getId());
        model.addAttribute("password", mangerInfo.getPassword());
        model.addAttribute("mangerInfo", mangerInfo);

        return "/MyPage/managerPwChange";

    }

    @PostMapping
    public String userChangePassword(updateDto updatedto,
                                     @SessionAttribute(name = SessionConst.LoginId, required = false) String id,
                                     RedirectAttributes redirectAttributes) {
        int userUpdate = joinService.update(id, updatedto.getPassword());

        if (userUpdate == 1) {
            redirectAttributes.addAttribute("id", id);
            return "redirect:/MyPage/change/{id}";
        }

        joinService.mangerUpdate(id, updatedto.getPassword());
        redirectAttributes.addAttribute("id", id);
        return "redirect:/MyPage/change/{id}";

    }

    @GetMapping("/change/{id}")
    public String myInfo(@PathVariable("id") String id, Model model) {
        if (checkMangerOrUser.check(id) == 1) {
            //사용자
            userInfo findResult = joinService.myInfo(id);
            model.addAttribute("user", findResult);
        } else {
            //관리자
            mangerInfo mangerInfo = joinService.mangerInfo(id);
            model.addAttribute("user", mangerInfo);
        }

        return "/MyPage/changeSuccess";
    }

    // 회원탈퇴
    @PostMapping("/delete")
    public String deleteUser(@SessionAttribute(name = SessionConst.LoginId, required = false) String id) {
        if (checkMangerOrUser.check(id) == 1) {
            // 유저 회원탈퇴
            joinService.delete(id);
            return "redirect:/MainPage";
        }
        // 관리자 회원탈퇴
        joinService.deleteManger(id);
        return "redirect:/MainPage";

    }
}
