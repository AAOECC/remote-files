package usercase.service.impl;

import usercase.dao.UserDao;
import usercase.dao.impl.UserDaoImpl;
import usercase.domain.User;
import usercase.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> findAll() {

        List<User> userList = userDao.findAll();

        return userList;
    }

    @Override
    public User findUser(User user) {
        return userDao.findUser(user);
    }

    @Override
    public boolean addUser(User addUser) {
        boolean flag = userDao.addUser(addUser);
        return flag;
    }

    @Override
    public boolean delUser(User delUser) {
        return userDao.delUser(delUser);
    }

    @Override
    public boolean updateUser(User updateUser) {
        return userDao.updateUser(updateUser);
    }
}
