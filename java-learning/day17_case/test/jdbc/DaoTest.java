package jdbc;

import org.junit.Test;
import usercase.domain.User;
import usercase.dao.impl.UserDaoImpl;

import java.util.List;

public class DaoTest {

    @Test
    public void dbConnection(){
        UserDaoImpl userDaoImpl = new UserDaoImpl();

        List<User> allUser = userDaoImpl.findAll();
        for (User user : allUser) {
            System.out.println(user);
        }
    }
}
