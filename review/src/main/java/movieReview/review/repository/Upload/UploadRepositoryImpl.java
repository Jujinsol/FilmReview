package movieReview.review.repository.Upload;

import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.photoUriInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

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
    public photoUriInfo select(photoUriInfo photoUriInfo) {
        return null;
    }
}
