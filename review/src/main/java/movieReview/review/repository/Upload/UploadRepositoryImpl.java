package movieReview.review.repository.Upload;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.FileInfo.photoUriInfo;
import movieReview.review.Domain.MovieInfo.movieInfo;
import movieReview.review.Domain.ReviewInfo.JpaRevieTab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
@RequiredArgsConstructor
public class UploadRepositoryImpl implements UploadRepository{

    private final JdbcTemplate template;

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
        Map<Integer,String> sql = new HashMap<>();
        sql.put(0,"DELETE FROM photoinfo WHERE photoOriName = ?");
        sql.put(1,"DELETE FROM reviewTab WHERE photoOriName=?");

        for(int i = 0; i<sql.size(); i++){
            result = template.update(
                    sql.get(i),
                    movieinfo.getPhotoOriName()
            );
        }
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
