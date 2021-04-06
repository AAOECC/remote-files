import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * 测试 Jedis 操纵 redis 数据库
 *
 */
public class JedisTest {

    @Test
    public void test01(){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.set("username","zhangsan");
        jedis.close();
    }
}
