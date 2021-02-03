package jdbcTemplaste;

import static org.junit.Assert.assertEquals;

import domain.Emp;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import java.util.List;
import java.util.Map;

/**
 *  1. 修改1号数据的 salary 为 10000
 *  2. 添加一条记录
 * 	3. 删除刚才添加的记录
 * 	4. 查询id为1的记录，将其封装为Map集合
 * 	5. 查询所有记录，将其封装为List
 * 	6. 查询所有记录，将其封装为Emp对象的List集合
 * 	7. 查询总记录数
 */
public class JdbcTemplateDemo2 {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 1. 修改1号数据的 salary 为 10000
     */
    @Test
    public void test1(){
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

        String sql = "update emp set salary=10000 where id = 1001";

        int count = jdbcTemplate.update(sql);
        assertEquals(count, 1);
    }

    /**
     * 2. 添加一条记录
     */
    @Test
    public void test2(){
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "insert into emp (id, ename,job_id, dept_id) value (1015, 'zhaoyun',3, 30)";
        int count = jdbcTemplate.update(sql);
        assertEquals(count, 1);
    }

    /**
     * 3. 删除刚才添加的记录
     */
    @Test
    public void test3(){
        String sql = "delete from emp where id = ?";
        int count = jdbcTemplate.update(sql, 1015);
        assertEquals(count, 1);
    }

    /**
     * 4. 查询id为1001的记录，将其封装为Map集合
     */
    @Test
    public void Test4(){
        String sql = "select * from emp where id = ?";
        Map<String, Object> empMap = jdbcTemplate.queryForMap(sql, 1001);
        System.out.println(empMap);
    }

    /**
     * 5. 查询所有记录，将其封装为List
     */
    @Test
    public void Test5(){
        String sql = "select * from emp";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> stringObjectMap : mapList) {
            System.out.println(stringObjectMap);
        }
    }

    /**
     * 6. 查询所有记录，将其封装为Emp对象的List集合
     */
    @Test
    public void Test6(){
        String sql = "select * from emp";
        List<Emp> empList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Emp>(Emp.class));
        for (Emp emp : empList) {
            System.out.println(emp);
        }
    }

    /**
     * 7. 查询总记录数
     */
    @Test
    public void test7(){
        String sql = "select count(id) from emp";
        Long total = jdbcTemplate.queryForObject(sql, Long.class);
        System.out.println(total);
    }
}
