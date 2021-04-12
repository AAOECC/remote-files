package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查询数据库是否存在
     * @param user
     * @return 查询到的对象
     */
    @Override
    public User findByUsername(User user) {
        User resUser = null;
        try {
            String sql  = "select * from tab_user where username = ?";
            resUser = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), user.getUsername());
        } catch (Exception e) {

        }
        return resUser;
    }

    /**
     * 数据库中插入接收到 user 对象信息
     * @param user
     * @return 是否成功
     */
    @Override
    public boolean save(User user) {
        String sql = "insert into tab_user (username, password, name, birthday, sex, telephone, email) values (?,?,?,?,?,?,?)";
        int count = jdbcTemplate.update(sql, user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail());
        return count > 0;
    }
}
