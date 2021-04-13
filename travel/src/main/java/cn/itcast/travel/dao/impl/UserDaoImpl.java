package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

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
            String sql  = "select * from tab_user where binary username = ?";
            resUser = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), user.getUsername());
        } catch (Exception e) {

        }
        return resUser;
    }

    /**
     * 根据激活码查询 User
     * @param user 需要查询的对象
     * @return 查询的数据库对象
     */
    @Override
    public User findByCode(User user) {
        User resUser = null;
        try {
            String sql  = "select * from tab_user where code = ?";
            resUser = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), user.getCode());
        } catch (Exception e) {

        }
        return resUser;
    }

    @Override
    public User findByUsernameAndPwd(User user) {
        User db_user = null;
        try {
            String sql = "select * from tab_user where binary username=? and binary password=?";
            db_user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), user.getUsername(), user.getPassword());
        } catch (Exception e) {

        }
        return db_user;
    }

    /**
     * 数据库中插入接收到 user 对象信息
     * @param user
     * @return 是否成功
     */
    @Override
    public boolean save(User user) {
        String sql = "insert into tab_user (username, password, name, birthday, sex, telephone, email,status,code) values (?,?,?,?,?,?,?,?,?)";
        int count = jdbcTemplate.update(sql, user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode());
        return count > 0;
    }

    /**
     * 更新用户激活状态
     * @param user 用户，
     */
    @Override
    public void updateStatus(User user) {
        String sql = "update tab_user set status = ? where uid= ?";
        jdbcTemplate.update(sql, "Y", user.getUid());
    }
}
