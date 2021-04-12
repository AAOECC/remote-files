package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    /**
     * 注册user
     *
     * @param user
     * @return 是否注册成功
     */
    @Override
    public boolean registUser(User user) {
        /*
        1.查询数据库中是否存在 相同name值
            名字是否可用
        2.调用方法进行注册
         */
        User db_user = userDao.findByUsername(user);
        if (db_user != null) {
            return false;
        }

        //进行注册
        return userDao.save(user);
    }
}
