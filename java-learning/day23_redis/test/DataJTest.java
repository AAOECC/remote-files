import dao.ProvinceDao;
import dao.impl.ProvinceDaoImpl;
import domain.Province;
import org.junit.Test;

import java.util.List;

public class DataJTest {

    @Test
    public void ProTest(){
        ProvinceDao provinceDao = new ProvinceDaoImpl();
        List<Province> all = provinceDao.findAll();
        System.out.println(all);

    }
}

