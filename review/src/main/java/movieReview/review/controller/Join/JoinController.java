package movieReview.review.controller.Join;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.mangerInfo;
import movieReview.review.dto.userInfo;
import movieReview.review.service.Join.joinServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/Join")
public class JoinController {
    private final joinServiceImpl joinService;

    @GetMapping
    public String joinPage(@ModelAttribute("userInfo") userInfo userinfo, @ModelAttribute("mangerInfo") mangerInfo mangerinfo){
        return "Join/signUp";
    }

    @PostMapping
    public String joinUser(userInfo userinfo, mangerInfo mangerinfo, BindingResult bindingResult, RedirectAttributes redirectAttributes,Model model){
        log.info("userinfo.getId()={}",userinfo.getId());
        log.info("userinfo.getPassword()={}",userinfo.getPassword());
        log.info("userinfo.getEmail()={}",userinfo.getEmail());

        if(userinfo.getId().isEmpty()){
            bindingResult.addError(new FieldError("userInfo", "id", userinfo.getId(), false, new String[]{"login.id"}, null, null));
        }
        if(userinfo.getPassword()==null){
            bindingResult.addError(new FieldError("userInfo","password",userinfo.getPassword(),false,new String[]{"login.password"},new Object[]{45},null));
        }
        if(userinfo.getEmail().isEmpty()){
            bindingResult.addError(new FieldError("userInfo","email",userinfo.getEmail(),false,new String[]{"login.email"},null,null));
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("errors",bindingResult);
            log.info("errors={}",bindingResult);
            return "Join/signUp";
        }else {
            if (mangerinfo.getNumber() == null) {
                // 사용자일경우
                int join = joinService.join(userinfo.getEmail(), userinfo.getId(), userinfo.getPassword(), null);

                if(join==1){
                    userInfo joinResult = joinService.myInfo(userinfo.getId());
                    redirectAttributes.addAttribute("id", joinResult.getId());
                    redirectAttributes.addAttribute("password",joinResult.getPassword());
                    redirectAttributes.addAttribute("email", joinResult.getEmail());

                    return "redirect:/Join/{id}";
                }else{
                    bindingResult.addError(new FieldError("userInfo","id",userinfo.getId(),false,new String[]{"login.id.equal"},null,null));
                    log.info("errors={}",bindingResult);
                    return "Join/signUp";
                }
            } else {
                // 관리자일경우
                int mangerJoin = joinService.join(userinfo.getEmail(), userinfo.getId(), userinfo.getPassword(), mangerinfo.getNumber());

                if(mangerJoin==1) {
                    mangerInfo joinMangerResult = joinService.mangerInfo(userinfo.getId());
                    redirectAttributes.addAttribute("id", joinMangerResult.getId());
                    redirectAttributes.addAttribute("password",joinMangerResult.getPassword());
                    redirectAttributes.addAttribute("number", joinMangerResult.getNumber());
                    redirectAttributes.addAttribute("email", joinMangerResult.getEmail());

                    return "redirect:/Join/Man/{id}";
                }else{
                    bindingResult.addError(new FieldError("userInfo","id",mangerinfo.getId(),false,new String[]{"login.id.equal"},null,null));
                    log.info("errors={}",bindingResult);
                    return "Join/signUp";
                }

            }
        }
    }

    @GetMapping("/{id}")
    public String myInfo(@PathVariable("id") String id, Model model){
        userInfo userInfo = joinService.myInfo(id);
        model.addAttribute("userInfo",userInfo);
        return "/Join/signUpSuccess";
    }

    @GetMapping("/Man/{id}")
    public String MangerInfo(@PathVariable("id") String id, Model model){
        userInfo userInfo = joinService.myInfo(id);
        mangerInfo mangerInfo = joinService.mangerInfo(id);
        log.info("mangerInfo.getId()={}",mangerInfo.getId());

        model.addAttribute("userInfo",userInfo);
        model.addAttribute("mangerInfo",mangerInfo);
        return "/Join/MsignUpSuccess";
    }
}
