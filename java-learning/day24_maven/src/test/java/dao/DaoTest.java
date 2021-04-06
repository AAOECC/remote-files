package dao;

import domain.User;
import jdbc.UserDao;
import jdbc.impl.UserDaoImpl;
import org.junit.Test;

import java.util.List;

public class DaoTest {
    UserDao userDao = new UserDaoImpl();

    @Test
    public void Test1(){
        List<User> userList = userDao.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
