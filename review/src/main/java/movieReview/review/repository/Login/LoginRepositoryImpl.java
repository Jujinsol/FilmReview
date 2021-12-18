package movieReview.review.repository.Login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.Login.loginDto;
import movieReview.review.Domain.Login.loginMangerInfo;
import movieReview.review.Domain.Login.loginUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequiredArgsConstructor
public class LoginRepositoryImpl implements LoginRepository {

    private final JdbcTemplate template;

    // 유저로그인 체크
    @Override
    public loginDto userLoginCheck(loginDto dto, String sql) {
        List<loginDto> result = template.query(sql, new RowMapper<loginDto>() {
            @Override
            public loginDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                loginDto loginDto = new loginDto();
                loginDto.setId(rs.getString("userId"));
                loginDto.setPassword(rs.getString("userPw"));
                return loginDto;
            }
        }, dto.getId());
        return result.size() == 0 ? null : result.get(0);
    }


    // 관리자로그인 체크
    @Override
    public loginDto mangerLoginCheck(loginDto dto, String sql) {
        List<loginDto> result = template.query(sql, new RowMapper<loginDto>() {
            @Override
            public loginDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                loginDto loginDto = new loginDto();
                loginDto.setId(rs.getString("masterId"));
                loginDto.setPassword(rs.getString("pw"));
                return loginDto;
            }
        }, dto.getId());
        return result.size() == 0 ? null : result.get(0);
    }
}
