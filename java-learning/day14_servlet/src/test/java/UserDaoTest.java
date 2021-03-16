import com.example.dao.UserDao;
import com.example.domain.User;
import org.junit.jupiter.api.Test;

public class UserDaoTest {

    @Test
    public void loginTest(){
        UserDao userDao = new UserDao();
        User loginUser = new User();
        loginUser.setUsername("bb");
        loginUser.setPassword("123");

        User user = userDao.login(loginUser);
        System.out.println(user);
    }
}
