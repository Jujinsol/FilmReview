package movieReview.review.repository.reviewFunction.FindMovie;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.ReviewInfo.JpaRevieTab;
import movieReview.review.Domain.ReviewInfo.ReviewInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ReviewFunctionRepositoryImpl implements ReviewFunctionRepository {

    private final JdbcTemplate template;

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
        String sql = "delete from reviewTab where reviewUser = ? AND photoOriName = ?";
        return template.update(sql,
                reviewInfo.getReviewUser(),
                reviewInfo.getPhotoOriName()
        );
    }

    // 여러행 조회
    @Override
    public List<JpaRevieTab> selectReview(ReviewInfo reviewInfo) {
        String sql = "select * from reviewTab where photoOriName = ?";
        return template.query(sql, (rs, rowNum) -> {
            JpaRevieTab reviews = new JpaRevieTab();
            reviews.setPhotoOriName(rs.getString("photoOriName"));
            reviews.setReviewUser(rs.getString("reviewUser"));
            reviews.setMovieReivew(rs.getString("movieReview"));
            reviews.setMoviePoint(rs.getInt("moviePoint"));
            return reviews;
        }, reviewInfo.getPhotoOriName());
    }

    @Override
    public List<ReviewInfo> selectMyReviews(ReviewInfo reviewInfo) {
        String sql = "select * from reviewTab where reviewUser = ?";
        return template.query(sql, (rs, rowNum) -> {
            ReviewInfo reviewInfo1 = new ReviewInfo();
            reviewInfo1.setPhotoOriName(rs.getString("photoOriName"));
            reviewInfo1.setReviewUser(rs.getString("reviewUser"));
            reviewInfo1.setMovieReivew(rs.getString("movieReview"));
            reviewInfo1.setMoviePoint(rs.getInt("moviePoint"));
            return reviewInfo1;
        },reviewInfo.getReviewUser());
    }
}
