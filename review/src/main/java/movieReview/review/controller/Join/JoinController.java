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
    private final joinValidation joinValidation;

    /**
     * @InitBinder public void init(WebDataBinder dataBinder){
     * dataBinder.addValidators(joinValidation);
     * }
     * 내프로젝트에서는 얘 못쓴다.
     * 왜냐면 검증코드에 들어가야하는 dto의 종류가 두가지니까.
     *
     * mangerInfo랑 userInfo랑 둘다 섞여서 회원가입이 되는데,
     * 둘은 상속관계를 가지고있지도 않고, 부모가 같지도 않기때문에
     * @InitBinder를 사용할때 모든 메서드가 실행하기전에 안에있는 검증코드가 자동으로 실행된다.
     * 그때 joinPage메서드가 실행되면서 mangerInfo도 넘겨주는데,
     *
     * 나의 검증코드에는 userInfo만 return해주고있기 때문이다.
     * 그래서 nullPointException이 발생한다.
     *
     * 여러개의 class를 return하는방법은 모르겠다..
     **/

    @GetMapping
    public String joinPage(@ModelAttribute("userInfo") userInfo userinfo, @ModelAttribute("mangerInfo") mangerInfo mangerinfo) {
        return "Join/signUp";
    }

    @PostMapping
    public String joinUser( userInfo userinfo, mangerInfo mangerinfo, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        joinValidation.validate(userinfo, bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
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
                bindingResult.rejectValue("id", "equal");
                log.info("errors={}", bindingResult);
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
                bindingResult.rejectValue("id", "equal");
                log.info("errors={}", bindingResult);
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
