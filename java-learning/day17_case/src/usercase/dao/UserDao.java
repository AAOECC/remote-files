package usercase.dao;

import usercase.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    public List<User> findAll();

    public User findUser(User user);

    public boolean addUser(User addUser);

    public boolean delUser(User delUser);

    public boolean updateUser(User updateUser);

    /**
     * 按页码查询
     * @param begin
     * @param count
     * @return
     */
    public List<User> findByPage(int begin, int count,Map<String,String[]> condition);

    /**
     * 查询数据总量
     * @return
     */
    public long countAll(Map<String,String[]> condition);
}