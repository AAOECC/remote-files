package dao;

import domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import java.util.List;

/**
 * User 表 操纵方法
 */
public class UserDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 取得数据库中的 User 对象
     * 若没有，返回 null
     * @param loginUser 用户名
     * @return User
     */
    public User getUser(User loginUser){
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();
        //查询字符串
        String sql_bin = "select * from user where binary username=? and binary password=?";
        //数据库查询
        List<User> userList = jdbcTemplate.query(sql_bin, new BeanPropertyRowMapper<>(User.class),
                username, password);
        if (userList.size() != 0){
            return userList.get(0);
        } else {
            return null;
        }
    }

}

