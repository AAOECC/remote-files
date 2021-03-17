package jdbc;

import org.junit.Test;
import usercase.dao.User;
import usercase.domain.UserDao;

import java.util.List;

public class DaoTest {

    @Test
    public void dbConnection(){
        UserDao userDao = new UserDao();

        List<User> allUser = userDao.getAllUser();
        for (User user : allUser) {
            System.out.println(user);
        }
    }
}
