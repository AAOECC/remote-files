package usercase.service.impl;

import usercase.dao.UserDao;
import usercase.dao.impl.UserDaoImpl;
import usercase.domain.User;
import usercase.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public List<User> findAll() {

        UserDao userDao = new UserDaoImpl();
        List<User> userList = userDao.findAll();

        return userList;
    }
}
