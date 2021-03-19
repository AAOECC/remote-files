package usercase.dao;

import usercase.domain.User;

import java.util.List;

public interface UserDao {

    public List<User> findAll();

    public User findUser(User user);

    public boolean addUser(User addUser);

    public boolean delUser(User delUser);

    public boolean updateUser(User updateUser);
}
