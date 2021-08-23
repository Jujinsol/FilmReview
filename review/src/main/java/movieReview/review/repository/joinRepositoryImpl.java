package movieReview.review.repository;

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

@Slf4j
@Repository
public class joinRepositoryImpl implements joinRepository{


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

    public joinRepositoryImpl(){

        log.info("dirve={}",driver);
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(userid);
        dataSource.setPassword(userpw);

        template = new JdbcTemplate();
        template.setDataSource(dataSource);
    }

    @Override
    public int createUser(userInfo userinfo) {
        int result = 0;
        final String sql = "INSERT INTO user (userId, userPw, userEmail) values (?,?,?)";
        result = template.update(sql,
                userinfo.getId(),
                userinfo.getPassword(),
                userinfo.getEmail());

        return result;
    }

    @Override
    public int createManger(mangerInfo mangerinfo) {
        int result = 0;
        final String sql = "INSERT INTO master (masterId, number, pw, email) values (?,?,?,?)";
        result = template.update(sql,
                mangerinfo.getId(),
                mangerinfo.getNumber(),
                mangerinfo.getPassword(),
                mangerinfo.getEmail());

        return result;
    }

    @Override
    public userInfo selectMyinfo(userInfo userinfo) {
        List<userInfo> stocks = null;
        final String sql = "SELECT * FROM user WHERE userId = ?";

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
    public int updateMyinfo(userInfo userinfo) {
        int result = 0;

        final String sql = "UPDATE user SET userPw = ? WHERE userId = ?";
        // select를 제외한 CUD들은 template.update를 사용하면 된다.
        result = template.update(sql,
                userinfo.getPassword(),
                userinfo.getId()
        );

        return result;
    }

    @Override
    public int delete(userInfo userinfo) {
        int result = 0;

        final String sql = "DELETE FROM user WHERE userId = ?";
        result = template.update(sql,
                userinfo.getId()
        );

        return result;
    }

    @Override
    public int deleteManger(mangerInfo mangerinfo){
        int result = 0;


        final String sql = "DELETE FROM master WHERE masterId = ?";
        result = template.update(sql,
                mangerinfo.getId()
        );

        return result;
    }

}
