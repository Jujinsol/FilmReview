package movieReview.review.repository.Login;

import lombok.extern.slf4j.Slf4j;
import movieReview.review.dto.mangerInfo;
import movieReview.review.dto.userInfo;
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
public class LoginRepositoryImpl implements LoginRepository{

    //    @Value("${spring.datasource.driver-class-name}")
    private String driver="com.mysql.cj.jdbc.Driver";
    //    @Value("${spring.datasource.username}")
    private String userid="root";
    //    @Value("${spring.datasource.url}")
    private String url="jdbc:mysql://localhost:3306/moviereview?serverTimezone=UTC&characterEncoding=UTF-8";
    //    @Value("${spring.datasource.password}")
    private String userpw="1234";

    private DriverManagerDataSource dataSource;
    private JdbcTemplate template;


    public LoginRepositoryImpl(){

        log.info("dirve={}",driver);
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(userid);
        dataSource.setPassword(userpw);

        template = new JdbcTemplate();
        template.setDataSource(dataSource);
    }

    // 유저로그인 체크
    @Override
    public userInfo userLoginCheck(userInfo userinfo, String usersql) {
        List<userInfo> stocks = null;

        stocks = template.query(usersql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userinfo.getId());
                pstmt.setInt(2, userinfo.getPassword());

            }
        }, new RowMapper<userInfo>() {

            @Override
            public userInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                userinfo.setId(rs.getString("userId"));
                userinfo.setPassword(rs.getInt("userPw"));
                userinfo.setEmail(rs.getString("userEmail"));
                return userinfo;
            }

        });
        if (stocks.isEmpty()) {
            return null;
        } else {
            return userinfo;
        }
    }


    // 관리자로그인 체크
    @Override
    public mangerInfo mangerLoginCheck(mangerInfo mangerinfo, String mastersql) {
        List<mangerInfo> mang = null;

        mang = template.query(mastersql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, mangerinfo.getId());
                pstmt.setInt(2,mangerinfo.getPassword());

            }
        }, new RowMapper<mangerInfo>() {

            @Override
            public mangerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                mangerinfo.setId(rs.getString("masterId"));
                mangerinfo.setNumber(rs.getInt("number"));
                mangerinfo.setPassword(rs.getInt("pw"));
                mangerinfo.setEmail(rs.getString("email"));

                return mangerinfo;
            }

        });
        if (mang.isEmpty()) {
            return null;
        } else {
            return mangerinfo;
        }
    }

    @Override
    public userInfo userIdCheck(userInfo userinfo, String sql) {
        List<userInfo> stocks = null;

        stocks = template.query(sql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userinfo.getId());

            }
        }, new RowMapper<userInfo>() {

            @Override
            public userInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                userinfo.setId(rs.getString("userId"));
                userinfo.setPassword(rs.getInt("userPw"));
                userinfo.setEmail(rs.getString("userEmail"));
                return userinfo;
            }

        });
        if (stocks.isEmpty()) {
            return null;
        } else {
            return userinfo;
        }
    }

    @Override
    public mangerInfo mangerIdCheck(mangerInfo mangerinfo, String sql) {
        List<mangerInfo> mang = null;

        mang = template.query(sql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, mangerinfo.getId());
            }
        }, new RowMapper<mangerInfo>() {

            @Override
            public mangerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                mangerinfo.setId(rs.getString("masterId"));
                mangerinfo.setNumber(rs.getInt("number"));
                mangerinfo.setPassword(rs.getInt("pw"));
                mangerinfo.setEmail(rs.getString("email"));

                return mangerinfo;
            }

        });
        if (mang.isEmpty()) {
            return null;
        } else {
            return mangerinfo;
        }
    }

    @Override
    public userInfo userPwCheck(userInfo userinfo, String sql) {
        List<userInfo> stocks = null;

        stocks = template.query(sql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setInt(1, userinfo.getPassword());

            }
        }, new RowMapper<userInfo>() {

            @Override
            public userInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                userinfo.setId(rs.getString("userId"));
                userinfo.setPassword(rs.getInt("userPw"));
                userinfo.setEmail(rs.getString("userEmail"));
                return userinfo;
            }

        });
        if (stocks.isEmpty()) {
            return null;
        } else {
            return userinfo;
        }
    }

    @Override
    public mangerInfo mangerPwCheck(mangerInfo mangerinfo, String sql) {
        List<mangerInfo> mang = null;

        mang = template.query(sql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setInt(1,mangerinfo.getPassword());

            }
        }, new RowMapper<mangerInfo>() {

            @Override
            public mangerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                mangerinfo.setId(rs.getString("masterId"));
                mangerinfo.setNumber(rs.getInt("number"));
                mangerinfo.setPassword(rs.getInt("pw"));
                mangerinfo.setEmail(rs.getString("email"));

                return mangerinfo;
            }

        });
        if (mang.isEmpty()) {
            return null;
        } else {
            return mangerinfo;
        }
    }

}
