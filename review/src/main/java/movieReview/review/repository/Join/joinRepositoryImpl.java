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
public class joinRepositoryImpl implements joinRepository {

    private final JdbcTemplate template;

    @Autowired
    public joinRepositoryImpl(JdbcTemplate template) {
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
        String sql = "SELECT * FROM user WHERE userId = ?";
        List<userInfo> result = template.query(
                sql, (rs, rowNum) -> {
                    userInfo user = new userInfo();
                    user.setId(rs.getString("userId"));
                    user.setPassword(rs.getString("userPw"));
                    user.setEmail(rs.getString("userEmail"));
                    return user;
                }, userinfo.getId()
        );
        return result.size() != 0 ? result.get(0) : null;
    }

    // 관리자 정보확인
    @Override
    public mangerInfo selectMangerinfo(mangerInfo mangerinfo) {
        String sql = "SELECT * FROM master WHERE masterId = ?";
        List<mangerInfo> result = template.query(sql, (rs, rowNum) -> {
            mangerInfo manager = new mangerInfo();
            manager.setId(rs.getString("masterId"));
            manager.setPassword(rs.getString("pw"));
            manager.setEmail(rs.getString("email"));
            manager.setNumber(rs.getInt("number"));
            return manager;
        }, mangerinfo.getId());
        return result.size() != 0 ? result.get(0) : null;
    }

    // 유저 비밀번호 변경
    @Override
    public int updateMyinfo(userInfo userinfo) {
        String sql = "UPDATE user SET userPw = ? WHERE userId = ?";
        return template.update(sql,
                userinfo.getPassword(),
                userinfo.getId()
        );
    }

    @Override
    public int updateMangInfo(mangerInfo mangerinfo) {
        String sql = "UPDATE master SET pw = ? WHERE masterId = ?";
        return template.update(sql,
                mangerinfo.getPassword(),
                mangerinfo.getId()
        );
    }

    // 유저 회원 탈퇴
    @Override
    public int delete(userInfo userinfo) {
        String sql = "DELETE FROM user WHERE userId = ?";
        return template.update(sql,
                userinfo.getId()
        );
    }

    // 관리자 회원 탈퇴
    @Override
    public int deleteManger(mangerInfo mangerinfo) {
        String sql = "DELETE FROM master WHERE masterId = ?";
        return template.update(sql,
                mangerinfo.getId()
        );
    }
}
