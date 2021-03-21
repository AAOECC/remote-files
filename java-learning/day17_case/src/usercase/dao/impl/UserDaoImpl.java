package usercase.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import usercase.dao.UserDao;
import usercase.domain.User;
import usercase.util.JDBCUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @Override
    public List<User> findByPage(int begin, int count, Map<String,String[]> condition) {
        String sql = "select * from user where 1=1";

        Set<String> keys = condition.keySet();
        StringBuilder sb = new StringBuilder(sql);
        List<Object> values = new ArrayList<>();
        for (String key : keys) {
            if (key.equals("page") || key.equals("rows")){
                continue;
            }

            String value = condition.get(key)[0];

            if (!("".equals(value) || value==null)){
                sb.append(" and "+key+" like ? ");
                values.add("%"+value+"%");
            }
        }

        sb.append(" limit ?, ?");
        values.add(begin);
        values.add(count);

        List<User> userList = jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper<User>(User.class), values.toArray());
        return userList;
    }

    @Override
    public long countAll(Map<String,String[]> condition) {
        String sql = "select count(*) from user where 1=1 ";
        //拼接sql, 根据查询的参数名
        Set<String> keys = condition.keySet();
        StringBuilder sb = new StringBuilder(sql);
        List<String> values = new ArrayList<>();
        for (String key : keys) {
            if (key.equals("page") || key.equals("rows")){
                continue;
            }

            String value = condition.get(key)[0];

            if (!("".equals(value) || value==null)){
                sb.append(" and "+key+" like ? ");
                values.add("%"+value+"%");
            }
        }
        System.out.println(sb);
        System.out.println(values);

        Long total = jdbcTemplate.queryForObject(sb.toString(), Long.class,values.toArray());
        return total;
    }
}
