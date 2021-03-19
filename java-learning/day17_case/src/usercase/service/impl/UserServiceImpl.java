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

        return userDao.findAll();
    }

    @Override
    public User findUser(User user) {
        return userDao.findUser(user);
    }

    @Override
    public boolean addUser(User addUser) {
        return userDao.addUser(addUser);
    }

    @Override
    public boolean delUser(User delUser) {
        return userDao.delUser(delUser);
    }

    @Override
    public boolean updateUser(User updateUser) {
        return userDao.updateUser(updateUser);
    }

    @Override
    public void delCheckedUser(String[] ids) {
        User delUser = new User();
        for (String id : ids) {
            delUser.setId(Integer.parseInt(id));
            userDao.delUser(delUser);
        }
    }
}
