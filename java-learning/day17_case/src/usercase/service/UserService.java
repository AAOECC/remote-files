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
}
