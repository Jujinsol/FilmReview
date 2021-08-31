package movieReview.review.repository.Upload;

import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.FileInfo.photoUriInfo;
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
    private String url="jdbc:mysql://localhost:3306/moviereview ?serverTimezone=UTC&characterEncoding=UTF-8";
    private String userpw="1234";

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
    public int insert(photoUriInfo photoUriinfo) {
        int result = 0;
        final String sql = "INSERT INTO photoinfo (photoOriName, photoUri) values (?,?)";
        result = template.update(sql,
                photoUriinfo.getPhotoOriName(),
                photoUriinfo.getPhotoUri());

        return result;
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
}
