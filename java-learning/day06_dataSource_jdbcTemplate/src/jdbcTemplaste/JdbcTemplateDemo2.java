package jdbcTemplaste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

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


    /**
     * 1. 修改1号数据的 salary 为 10000
     */
    @Test
    public void test1(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

        String sql = "update emp set salary=10000 where id = 1001";

        int count = jdbcTemplate.update(sql);
        assertEquals(count, 1);
    }

    /**
     * 2. 添加一条记录
     */
    @Test
    public void test2(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "insert into emp value";
        int count = jdbcTemplate.update(sql);
        assertEquals(count, 1);
    }
}
