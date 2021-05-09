package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

public interface UserService {

    /**
     * 注册 User
     * @param user
     * @return 是否注册成功
     */
    public boolean registUser(User user);

    /**
     * 激活用户
     * @param code 激活码
     * @return int 0: 激活码错误
     *             1: 激活成功
     */
    public int active(String code);

    /**
     * 登陆方法
     * @param user
     * @return
     */
    public User login(User user);
}
