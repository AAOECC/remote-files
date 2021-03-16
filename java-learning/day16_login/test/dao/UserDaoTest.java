package dao;

import domain.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void getUser_Test(){
        User loginUser = new User();
        loginUser.setUsername("bob");
        loginUser.setPassword("123");
        UserDao userDao = new UserDao();
        User user = userDao.getUser(loginUser);
        System.out.println(user);
    }
}
