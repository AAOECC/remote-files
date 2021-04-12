package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

public interface UserService {

    /**
     * 注册 User
     * @param user
     * @return 是否注册成功
     */
    public boolean registUser(User user);
}
