package movieReview.review.repository.GetMovieInfo;

import movieReview.review.dto.MovieInfo.movieInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class getMovieRepositoryImpl implements getMovieRepository{
    private String driver="com.mysql.cj.jdbc.Driver";
    private String userid="root";
    private String url="jdbc:mysql://118.67.133.219:3306/moviereview?serverTimezone=UTC&characterEncoding=UTF-8";
    private String userpw="1234wlstjddl";

    private DriverManagerDataSource dataSource;
    private JdbcTemplate template;


    public getMovieRepositoryImpl(){

        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(userid);
        dataSource.setPassword(userpw);

        template = new JdbcTemplate();
        template.setDataSource(dataSource);
    }

    @Override
    public movieInfo getMovieInfo(movieInfo movieinfo) {
        List<movieInfo> mang = null;
        final String sql = "SELECT * FROM photoinfo where movieName = ?";

        mang = template.query(sql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, movieinfo.getMovieName());

            }
        }, new RowMapper<movieInfo>() {

            @Override
            public movieInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                movieinfo.setPhotoOriName(rs.getString("photoOriName"));
                movieinfo.setPhotoUri(rs.getString("photoUri"));
                movieinfo.setMovieName(rs.getString("movieName"));
                movieinfo.setOpenYear(rs.getInt("openYear"));
                movieinfo.setStoryLine(rs.getString("storyLine"));
                movieinfo.setDirectorName(rs.getString(("directorName")));
                return movieinfo;
            }

        });
        if (mang.isEmpty()) {
            return null;
        } else {
            return movieinfo;
        }
    }
}
