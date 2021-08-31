package movieReview.review.controller.Join;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.mangerInfo;
import movieReview.review.dto.userInfo;
import movieReview.review.service.Join.joinServiceImpl;
import movieReview.review.validation.joinValidation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/Join")
public class JoinController {
    private final joinServiceImpl joinService;

    @GetMapping
    public String joinPage(@ModelAttribute("userInfo") userInfo userinfo, @ModelAttribute("mangerInfo") mangerInfo mangerinfo) {
        return "Join/signUp";
    }

    @PostMapping
    public String joinUser(@Validated @ModelAttribute userInfo userinfo, BindingResult userError, @ModelAttribute mangerInfo mangerinfo, BindingResult managerError, RedirectAttributes redirectAttributes) {
        if (managerError.hasErrors() || userError.hasErrors()) {
            log.info("errors={}", managerError);
            return "Join/signUp";
        }

        if (mangerinfo.getNumber() == null) {
            // 사용자일경우
            int join = joinService.join(userinfo.getEmail(), userinfo.getId(), userinfo.getPassword(), null);

            if (join == 1) {
                userInfo joinResult = joinService.myInfo(userinfo.getId());
                redirectAttributes.addAttribute("id", joinResult.getId());
                redirectAttributes.addAttribute("password", joinResult.getPassword());
                redirectAttributes.addAttribute("email", joinResult.getEmail());

                return "redirect:/Join/{id}";
            } else {
                userError.rejectValue("id", "equal");
                log.info("errors={}", userError);
                return "Join/signUp";
            }
        } else {
            // 관리자일경우
            int mangerJoin = joinService.join(userinfo.getEmail(), userinfo.getId(), userinfo.getPassword(), mangerinfo.getNumber());

            if (mangerJoin == 1) {
                mangerInfo joinMangerResult = joinService.mangerInfo(userinfo.getId());
                redirectAttributes.addAttribute("id", joinMangerResult.getId());
                redirectAttributes.addAttribute("password", joinMangerResult.getPassword());
                redirectAttributes.addAttribute("number", joinMangerResult.getNumber());
                redirectAttributes.addAttribute("email", joinMangerResult.getEmail());

                return "redirect:/Join/Man/{id}";
            } else {
                managerError.rejectValue("id", "equal");
                log.info("errors={}", managerError);
                return "Join/signUp";
            }


        }
    }

    @GetMapping("/{id}")
    public String myInfo(@PathVariable("id") String id, Model model) {
        userInfo userInfo = joinService.myInfo(id);
        model.addAttribute("userInfo", userInfo);
        return "/Join/signUpSuccess";
    }

    @GetMapping("/Man/{id}")
    public String MangerInfo(@PathVariable("id") String id, Model model) {
        userInfo userInfo = joinService.myInfo(id);
        mangerInfo mangerInfo = joinService.mangerInfo(id);
        log.info("mangerInfo.getId()={}", mangerInfo.getId());

        model.addAttribute("userInfo", userInfo);
        model.addAttribute("mangerInfo", mangerInfo);
        return "/Join/MsignUpSuccess";
    }
}
