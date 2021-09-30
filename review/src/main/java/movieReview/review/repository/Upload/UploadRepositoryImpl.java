package movieReview.review.repository.Upload;

import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.FileInfo.photoUriInfo;
import movieReview.review.Domain.MovieInfo.movieInfo;
import movieReview.review.Domain.ReviewInfo.JpaRevieTab;
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
@Slf4j
public class UploadRepositoryImpl implements UploadRepository{

    private String driver="com.mysql.cj.jdbc.Driver";
    private String userid="root";
    private String url="jdbc:mysql://118.67.133.219:3306/moviereview?serverTimezone=UTC&characterEncoding=UTF-8";
    private String userpw="1234wlstjddl";

    private DriverManagerDataSource dataSource;
    private JdbcTemplate template;


    public UploadRepositoryImpl(){

        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(userid);
        dataSource.setPassword(userpw);

        template = new JdbcTemplate();
        template.setDataSource(dataSource);
    }

    @Override
    public int insert(movieInfo movieinfo) {
        int result = 0;
        int result2 = 0;

        final String sql = "INSERT INTO photoinfo (photoOriName, photoUri, storyLine, movieName, openYear, directorName, trailerCode) values (?,?,?,?,?,?,?)";
        result = template.update(sql,
                movieinfo.getPhotoOriName(),
                movieinfo.getPhotoUri(),
                movieinfo.getStoryLine(),
                movieinfo.getMovieName(),
                movieinfo.getOpenYear(),
                movieinfo.getDirectorName(),
                movieinfo.getTrailerCode()
        );

        final String sql2 = "INSERT INTO reviewTab (photoOriName) values (?)";
        result2 =  template.update(sql2,
                movieinfo.getPhotoOriName());

        if(result == 1 && result2 == 1){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public photoUriInfo select(photoUriInfo photoUriinfo) {
        List<photoUriInfo> mang = null;
        final String sql = "SELECT photoUri FROM photoinfo WHERE photoOriName = ?";

        mang = template.query(sql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, photoUriinfo.getPhotoOriName());

            }
        }, new RowMapper<photoUriInfo>() {

            @Override
            public photoUriInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                photoUriinfo.setPhotoUri(rs.getString("photoUri"));
                return photoUriinfo;
            }

        });
        if (mang.isEmpty()) {
            return null;
        } else {
            return photoUriinfo;
        }
    }

    @Override
    public int movieDelete(movieInfo movieinfo) {
        int result = 0;

        final String sql = "DELETE FROM photoinfo WHERE photoOriName = ?";
        result = template.update(sql,
                movieinfo.getPhotoOriName()
        );

        return result;
    }

    @Override
    public List<photoUriInfo> findMyPhotoOriName(movieInfo movieInfo) {
        String sql = "select photoOriName from photoinfo where movieName=?";
        return template.query(
                sql, new RowMapper<photoUriInfo>() {
                    @Override
                    public photoUriInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                        photoUriInfo photoUriInfos = new photoUriInfo();
                        photoUriInfos.setPhotoOriName(rs.getString("photoOriName"));
                        return photoUriInfos;
                    }
                }, movieInfo.getMovieName()
        );
    }

}
