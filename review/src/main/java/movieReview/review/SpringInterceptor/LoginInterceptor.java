package movieReview.review.SpringInterceptor;

import lombok.extern.slf4j.Slf4j;
import movieReview.review.Session.SessionConst;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuffer requestURL = request.getRequestURL();
        HttpSession session = request.getSession();

        if (session == null || session.getAttribute(SessionConst.LoginId) == null) {
            log.info("preHandle run 로그인 안해서 통과 x");

            response.sendRedirect("/Login?redirectURL=" + requestURL);
            return false;
        }
        return true;
    }
}
