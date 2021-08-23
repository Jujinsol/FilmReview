package movieReview.review.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.mangerInfo;
import movieReview.review.dto.userInfo;
import movieReview.review.repository.joinRepositoryImpl;
import movieReview.review.service.joinServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.exceptions.ParserInitializationException;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/Join")
public class JoinController {
    private final joinServiceImpl joinService;

    @GetMapping
    public String joinPage(Model model){
        model.addAttribute("userInfo",new userInfo());
        model.addAttribute("mangerInfo",new mangerInfo());
        return "Join/signUp";
    }

    @PostMapping
    public String joinUser(@ModelAttribute userInfo userinfo, mangerInfo mangerinfo, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        System.out.println("userinfo.getId() = " + userinfo.getId());
        System.out.println("userinfo.getEmail() = " + userinfo.getEmail());
        System.out.println("userinfo.getPassword() = " + userinfo.getPassword());

        if(userinfo.getId().isEmpty()){
            bindingResult.addError(new FieldError("userinfo","id","id는 필수로 작성해야 합니다."));
        }
        if(userinfo.getPassword()==null){
            bindingResult.addError(new FieldError("userinfo","password","비밀번호는 필수로 작성해야 합니다."));
        }
        if(userinfo.getEmail().isEmpty()){
            bindingResult.addError(new FieldError("userinfo","email","이메일은 필수로 작성해야 합니다."));
        }

        if(bindingResult.hasErrors()){
            log.info("errors={}",bindingResult);
            return "Join/signUp";
        }else {
            if (mangerinfo.getNumber() == null) {
                joinService.join(userinfo.getEmail(), userinfo.getId(), userinfo.getPassword(), null);

                userInfo joinResult = joinService.myInfo(userinfo.getId());
                redirectAttributes.addAttribute("id", joinResult.getId());
                redirectAttributes.addAttribute("email", joinResult.getEmail());
                return "redirect:/Join/{id}";
            } else {
                joinService.join(userinfo.getEmail(), userinfo.getId(), userinfo.getPassword(), mangerinfo.getNumber());

                userInfo mangerInfo = joinService.myInfo(userinfo.getId());
                redirectAttributes.addAttribute("id", mangerInfo.getId());
                redirectAttributes.addAttribute("email", mangerInfo.getId());
                return "redirect:/Join/{id}";

            }
        }
    }

    @GetMapping("/{id}")
    public String myInfo(@PathVariable String id, Model model){
        userInfo userInfo = joinService.myInfo(id);
        model.addAttribute("userInfo",userInfo);
        return "/Join/signUpSuccess";
    }
}
