package usercase.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import usercase.dao.UserDao;
import usercase.domain.User;
import usercase.util.JDBCUtil;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());

    /**
     * User表中查询所有数据
     * @return
     */
    @Override
    public List<User> findAll(){
        String sql = "select * from user";

        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return userList;
    }

    @Override
    public User findUser(User user) {
        String sql = "select * from user where id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getId());
    }

    /**
     * User表中添加一列数据
     * @param user
     */
    public boolean addUser(User user){
        String sql = "insert into user (name, gender, age, address, qq, email) values (?,?,?,?,?,?)";
        int i = jdbcTemplate.update(sql,
                user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());

        return i>0;
    }

    /**
     * User表中删除数据
     * @param user
     */
    public boolean delUser(User user){
        String sql = "delete from user where id=?" ;
        int i = jdbcTemplate.update(sql, user.getId());

        return i>0;
    }

    /**
     * User表中修改数据
     * @param user
     * @return
     */
    public boolean updateUser(User user){
        String sql="update user set gender=?,age=?,address=?,qq=?,email=? where id=?";
        int i = jdbcTemplate.update(sql,
                user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());

        return i>0;
    }
}
