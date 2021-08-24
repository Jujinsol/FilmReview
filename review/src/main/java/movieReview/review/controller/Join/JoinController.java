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
                int join = joinService.join(userinfo.getEmail(), userinfo.getId(), userinfo.getPassword(), null);

                if(join==1){
                    userInfo joinResult = joinService.myInfo(userinfo.getId());
                    redirectAttributes.addAttribute("id", joinResult.getId());
                    redirectAttributes.addAttribute("email", joinResult.getEmail());
                }else{
                    bindingResult.addError(new FieldError("userinfo","id",userinfo.getId(),false,new String[]{"login.id.equal"},null,null));
                    log.info("errors={}",bindingResult);
                    return "Join/signUp";
                }
            } else {
                int mangerJoin = joinService.join(userinfo.getEmail(), userinfo.getId(), userinfo.getPassword(), mangerinfo.getNumber());

                if(mangerJoin==1) {
                    userInfo mangerInfo = joinService.myInfo(userinfo.getId());
                    redirectAttributes.addAttribute("id", mangerInfo.getId());
                    redirectAttributes.addAttribute("email", mangerInfo.getId());
                }else{
                    bindingResult.addError(new FieldError("userinfo","id",mangerinfo.getId(),false,new String[]{"login.id.equal"},null,null));
                    log.info("errors={}",bindingResult);
                    return "Join/signUp";
                }

            }
            return "redirect:/Join/{id}";
        }
    }

    @GetMapping("/{id}")
    public String myInfo(@PathVariable("id") String id, Model model){
        userInfo userInfo = joinService.myInfo(id);
        model.addAttribute("userInfo",userInfo);
        return "/Join/signUpSuccess";
    }
}
