import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import org.junit.Test;

public class DatabaseTest {


    @Test
    public void daoTest(){
        UserDao userDao = new UserDaoImpl();

        User user = new User();
        user.setName("test");

        User user1 = userDao.findByUsername(user);
        System.out.println(user1);
    }
}
