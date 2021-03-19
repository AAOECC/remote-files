package usercase.service;

import usercase.domain.User;

import java.util.List;

/**
 * 用户业务操作
 */
public interface UserService {

    /**
     * 返回数据库中的所有数据
     * @return
     */
    public List<User> findAll();

    /**
     * 查询数据库中的 特定user 信息
     * @param user
     * @return
     */
    public User findUser(User user);

    /**
     * 增加 user
     * @param addUser
     */
    public boolean addUser(User addUser);

    /**
     * 删除指定的 user 数据
     * @param delUser
     * @return
     */
    public boolean delUser(User delUser);

    /**
     * 更改数据库中指定 user 的值
     * @param updateUser
     * @return
     */
    public boolean updateUser(User updateUser);

}
