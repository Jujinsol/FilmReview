package movieReview.review.repository.reviewFunction.FindMovie;

import movieReview.review.dto.ReviewInfo.ReviewInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;


public class ReviewFunctionRepositoryImpl implements ReviewFunctionRepository {

    @Autowired
    private final JdbcTemplate template;
    private final EntityManager em;

    @Autowired
    public ReviewFunctionRepositoryImpl(JdbcTemplate template, EntityManager em) {
        this.template = template;
        this.em = em;
    }

    @Override
    public int insertReview(ReviewInfo reviewinfo) {
        String sql = "Update reviewTab set moviePoint = ? , movieReview =?, reviewUser=? where photoOriName = ?";
        return template.update(sql, reviewinfo.getMoviePoint(), reviewinfo.getMovieReivew(), reviewinfo.getReviewUser(), reviewinfo.getPhotoOriName());
    }

    @Override
    public int deleteReview(ReviewInfo reviewInfo) {
        String sql = "delete from reviewTab where reviewUser = ?";
        return template.update(sql,reviewInfo.getReviewUser());
    }
}
