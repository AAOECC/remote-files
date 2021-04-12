package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {

    /**
     * 查询数据库是否存在
     * @param user
     * @return 查询到的对象
     */
    public User findByUsername(User user);

    /**
     * 数据库中插入接收到 user 对象信息
     * @param user
     * @return 是否成功
     */
    public boolean save(User user);

}
