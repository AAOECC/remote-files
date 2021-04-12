import jedis.utils.JedisUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;
import java.util.Set;

public class JedisTest {

    private final Jedis jedis = new Jedis("localhost", 6379);
    /**
     * String 类型测试
     */
    @Test
    public void test(){

        jedis.set("username","zhangsan");
        String username = jedis.get("username");
        System.out.println(username);
    }

    /**
     * Hash 类型测试
     */
    @Test
    public void hashTest(){
        jedis.hset("persions","name","lisi");
        Map<String, String> persions = jedis.hgetAll("persions");
        System.out.println(persions);
    }

    /**
     * List 类型测试
     */
    @Test
    public void listTest(){
        jedis.lpush("myList","a","b","c");
        jedis.rpush("myList","a","b","c");

        System.out.println(jedis.lrange("myList",0,-1));

        System.out.println(jedis.lpop("myList"));
        System.out.println(jedis.rpop("myList"));
        System.out.println(jedis.lrange("myList",0,-1));
    }

    /**
     * set 类型测试
     */
    @Test
    public void SetTest(){
        jedis.sadd("program-language","java","c++","php","python");

        Set<String> smembers = jedis.smembers("program-language");
        System.out.println(smembers);
    }

    /**
     * sortedSet 类型测试
     */
    @Test
    public void SortedSetTest(){
        jedis.zadd("wangzhe",52,"猴子");
        jedis.zadd("wangzhe",32,"鲁班");
        jedis.zadd("wangzhe",3,"元歌");

        Set<String> wangzhe = jedis.zrevrange("wangzhe", 0, -1);//从大到小排列
        System.out.println(wangzhe);
    }

    /**
     * jedis pool 测试
     */
    @Test
    public void JPoolTest(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);
        jedisPoolConfig.setMaxIdle(10);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"localhost",6379);

        Jedis jedis = jedisPool.getResource();
        jedis.set("test1","001");

        System.out.println(jedis.get("test1"));
    }

    /**
     * jedis 工具类测试
     */
    @Test
    public void JUtilsTest(){
        Jedis jedis = JedisUtils.getJedis();
        jedis.set("utils","pass");
        System.out.println(jedis.get("utils"));
        JedisUtils.close(jedis);
    }

}
