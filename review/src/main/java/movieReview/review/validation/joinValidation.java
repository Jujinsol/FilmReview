package movieReview.review.validation;

import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.mangerInfo;
import movieReview.review.dto.userInfo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

@Slf4j
@Component
public class joinValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(userInfo.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        userInfo userinfo = (userInfo) target;
        int pwSize=0;
        if(!StringUtils.hasText(userinfo.getId())&&userinfo.getPassword()==null&&!StringUtils.hasText(userinfo.getEmail())){
            log.info("전체 안썻다");
            errors.reject("allEmpty",null);
        }
        if(userinfo.getPassword()!=null){
            pwSize = (int) Math.log10(userinfo.getPassword());
        }
        if(!StringUtils.hasText(userinfo.getId())){
            errors.rejectValue("id","requiredId",null);
        }
        if(userinfo.getPassword()==null && pwSize>=45){
            errors.rejectValue("password","requiredPw",new Object[]{45},null);
        }
        if(!StringUtils.hasText(userinfo.getEmail())){
            errors.rejectValue("email","requiredEmail",null);
        }

    }
}
