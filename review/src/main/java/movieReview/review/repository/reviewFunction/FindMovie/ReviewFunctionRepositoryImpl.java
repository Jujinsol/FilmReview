package movieReview.review.repository.reviewFunction.FindMovie;

import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.ReviewInfo.JpaRevieTab;
import movieReview.review.dto.ReviewInfo.ReviewInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
public class ReviewFunctionRepositoryImpl implements ReviewFunctionRepository {

    private final JdbcTemplate template;

    @Autowired
    public ReviewFunctionRepositoryImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public int insertReview(ReviewInfo reviewinfo) {
        String sql = "insert into reviewTab(photoOriName, moviePoint, movieReview, reviewUser) value (?,?,?,?)";
        return template.update(sql,
                reviewinfo.getPhotoOriName(),
                reviewinfo.getMoviePoint(),
                reviewinfo.getMovieReivew(),
                reviewinfo.getReviewUser()
        );
    }

    @Override
    public int deleteReview(ReviewInfo reviewInfo) {
        String sql = "delete from reviewTab where reviewUser = ?";
        return template.update(sql,reviewInfo.getReviewUser());
    }

    // 여러행 조회
    @Override
    public List<JpaRevieTab> selectReview(ReviewInfo reviewInfo) {
        String sql = "select * from reviewTab where photoOriName = ?";
        return template.query(
                sql, new RowMapper<JpaRevieTab>() {
                    @Override
                    public JpaRevieTab mapRow(ResultSet rs, int rowNum) throws SQLException {
                        JpaRevieTab reviews = new JpaRevieTab();
                        reviews.setPhotoOriName(rs.getString("photoOriName"));
                        reviews.setReviewUser(rs.getString("reviewUser"));
                        reviews.setMovieReivew(rs.getString("movieReview"));
                        reviews.setMoviePoint(rs.getInt("moviePoint"));
                        return reviews;
                    }
                }, reviewInfo.getPhotoOriName()
        );

    }
}
