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
    public String joinUser(userInfo userinfo, mangerInfo mangerinfo, BindingResult bindingResult, RedirectAttributes redirectAttributes,Model model){
        log.info("userinfo.getId()={}",userinfo.getId());
        log.info("userinfo.getPassword()={}",userinfo.getPassword());
        log.info("userinfo.getEmail()={}",userinfo.getEmail());

        if(userinfo.getId().isEmpty()){
            bindingResult.addError(new FieldError("userinfo", "id", userinfo.getId(), false, new String[]{"login.id"}, null, null));

        }
        if(userinfo.getPassword()==null){
            bindingResult.addError(new FieldError("userinfo","password",userinfo.getPassword(),false,new String[]{"login.password"},new Object[]{45},null));
        }
        if(userinfo.getEmail().isEmpty()){
            bindingResult.addError(new FieldError("userinfo","email",userinfo.getEmail(),false,new String[]{"login.email"},null,null));
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("errors",bindingResult);
            log.info("errors={}",bindingResult);
            return "Join/signUp";
        }else {
            if (mangerinfo.getNumber() == null) {
                joinService.join(userinfo.getEmail(), userinfo.getId(), userinfo.getPassword(), null);

                userInfo joinResult = joinService.myInfo(userinfo.getId());
                redirectAttributes.addAttribute("id", joinResult.getId());
                redirectAttributes.addAttribute("email", joinResult.getEmail());
            } else {
                joinService.join(userinfo.getEmail(), userinfo.getId(), userinfo.getPassword(), mangerinfo.getNumber());

                userInfo mangerInfo = joinService.myInfo(userinfo.getId());
                redirectAttributes.addAttribute("id", mangerInfo.getId());
                redirectAttributes.addAttribute("email", mangerInfo.getId());

            }
            return "redirect:/Join/{id}";
        }
    }

    @GetMapping("/{id}")
    public String myInfo(@PathVariable String id, Model model){
        userInfo userInfo = joinService.myInfo(id);
        model.addAttribute("userInfo",userInfo);
        return "/Join/signUpSuccess";
    }
}
