package movieReview.review.repository.Upload;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.FileInfo.photoUriInfo;
import movieReview.review.Domain.MovieInfo.movieInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class UploadRepositoryImpl implements UploadRepository {

    private final JdbcTemplate template;

    @Override
    public int insert(movieInfo movieinfo) {
        int result;
        int result2;

        String sql = "INSERT INTO photoinfo (photoOriName, photoUri, storyLine, movieName, openYear, directorName, trailerCode) values (?,?,?,?,?,?,?)";
        result = template.update(sql,
                movieinfo.getPhotoOriName(),
                movieinfo.getPhotoUri(),
                movieinfo.getStoryLine(),
                movieinfo.getMovieName(),
                movieinfo.getOpenYear(),
                movieinfo.getDirectorName(),
                movieinfo.getTrailerCode()
        );

        String sql2 = "INSERT INTO reviewTab (photoOriName) values (?)";
        result2 = template.update(sql2,
                movieinfo.getPhotoOriName());
        return result == 1 && result2 == 1 ? 1 : 0;
    }

    @Override
    public photoUriInfo select(photoUriInfo photoUriinfo) {
        String sql = "SELECT photoUri FROM photoinfo WHERE photoOriName = ?";
        List<photoUriInfo> result = template.query(sql, (rs, rowNum) -> {
            photoUriInfo info = new photoUriInfo();
            info.setPhotoUri(rs.getString("photoUri"));
            return info;
        }, photoUriinfo.getPhotoOriName());
        return result.size() != 0 ? result.get(0) : null;
    }

    @Override
    public int movieDelete(movieInfo movieinfo) {
        String sql = "DELETE FROM photoinfo WHERE photoOriName = ?";
        return template.update(sql,movieinfo.getPhotoOriName());
    }

    @Override
    public List<photoUriInfo> findMyPhotoOriName(movieInfo movieInfo) {
        String sql = "select photoOriName from photoinfo where movieName=?";
        return template.query(sql, (rs, rowNum) -> {
            photoUriInfo photoUriInfos = new photoUriInfo();
            photoUriInfos.setPhotoOriName(rs.getString("photoOriName"));
            return photoUriInfos;
        }, movieInfo.getMovieName());
    }

}
