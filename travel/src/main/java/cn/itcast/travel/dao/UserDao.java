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
     * 根据激活码查询 User
     * @param user 需要查询的对象
     * @return 查询的数据库对象
     */
    public User findByCode(User user);

    /**
     * 登陆用函数
     * @param user
     * @return
     */
    public User findByUsernameAndPwd(User user);


    /**
     * 数据库中插入接收到 user 对象信息
     * @param user 用户
     * @return 是否成功
     */
    public boolean save(User user);


    /**
     * 更新用户激活状态
     * @param user 用户，
     */
    public void updateStatus(User user);

}
