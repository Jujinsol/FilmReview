package movieReview.review.validation;

import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.userInfo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
public class LoginValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(userInfo.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        userInfo userinfo = (userInfo) target;

        if(userinfo.getPassword()==null&&!StringUtils.hasText(userinfo.getId())){
            errors.reject("allNull",null);
            log.info("둘다 미입력");
        }
        if(!StringUtils.hasText(userinfo.getId())){
            errors.rejectValue("id","idNull",null);
            log.info("id 미입력");
        }
        if(userinfo.getPassword()==null){
            errors.rejectValue("id","pwNull",null);
            log.info("pw 미입력");
        }
    }

}
