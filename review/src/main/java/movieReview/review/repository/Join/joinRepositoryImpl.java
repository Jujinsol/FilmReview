package movieReview.review.repository.Join;

import lombok.extern.slf4j.Slf4j;
import movieReview.review.Domain.mangerInfo;
import movieReview.review.Domain.userInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
public class joinRepositoryImpl implements joinRepository{

    private final JdbcTemplate template;
    @Autowired
    public joinRepositoryImpl(JdbcTemplate template){
        this.template = template;
    }


    // 유저회원가입
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

    // 관리자 회원가입
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

    // 내정보 확인 ( 유저 )
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

    // 관리자 정보확인
    @Override
    public mangerInfo selectMangerinfo(mangerInfo mangerinfo) {
        List<mangerInfo> mang = null;
        final String sql = "SELECT * FROM master WHERE masterId = ?";

        mang = template.query(sql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, mangerinfo.getId());

            }
        }, new RowMapper<mangerInfo>() {

            @Override
            public mangerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                mangerinfo.setId(rs.getString("masterId"));
                mangerinfo.setPassword(rs.getInt("pw"));
                mangerinfo.setEmail(rs.getString("email"));
                mangerinfo.setNumber(rs.getInt("number"));
                return mangerinfo;
            }

        });
        if (mang.isEmpty()) {
            return null;
        } else {
            return mangerinfo;
        }
    }

    // 유저 비밀번호 변경
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
    public int updateMangInfo(mangerInfo mangerinfo) {
        int result = 0;

        final String sql = "UPDATE master SET pw = ? WHERE masterId = ?";
        // select를 제외한 CUD들은 template.update를 사용하면 된다.
        result = template.update(sql,
                mangerinfo.getPassword(),
                mangerinfo.getId()
        );

        return result;
    }

    // 유저 회원 탈퇴
    @Override
    public int delete(userInfo userinfo) {
        int result = 0;

        final String sql = "DELETE FROM user WHERE userId = ?";
        result = template.update(sql,
                userinfo.getId()
        );

        return result;
    }

    // 관리자 회원 탈퇴
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
