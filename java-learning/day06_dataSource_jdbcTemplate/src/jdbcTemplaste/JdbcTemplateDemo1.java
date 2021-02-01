package jdbcTemplaste;

import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

/**
 * Spring JDBC 测试
 */
public class JdbcTemplateDemo1 {

    public static void main(String[] args) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

        //生成执行sql语句
        String sql = "update account set balance=2000 where id = ?";

        //执行语句
        int count = jdbcTemplate.update(sql, 4);
        System.out.println(count);
    }

}
