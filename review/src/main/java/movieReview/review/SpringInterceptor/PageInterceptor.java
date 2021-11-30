package movieReview.review.SpringInterceptor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component // 페이지정보 검사 ( 1페이지 이하로 떨어지면 Main으로 리다이렉트 )
public class PageInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String pageNumber = request.getParameter("page");
        log.info("pageNumber ={} ",pageNumber);

        if(pageNumber == null){
            response.sendRedirect("/?page="+0);
            return false;
        }else{
            int iNumber = Integer.parseInt(pageNumber);

            if(iNumber<0){
                response.sendRedirect("/?page="+0);
                return false;
            }
        }

        return true;
    }
}
