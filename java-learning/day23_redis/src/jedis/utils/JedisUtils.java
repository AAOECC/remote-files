package jedis.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisUtils {
    private static JedisPool jedisPool;

    static{
        InputStream is = JedisUtils.class.getResourceAsStream("/jedis.properties");
        Properties pro = new Properties();
        try {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
        jedisPoolConfig.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));

        jedisPool = new JedisPool(jedisPoolConfig,pro.getProperty("host"),Integer.parseInt(pro.getProperty("port")));
    }

    /**
     * 返回 jedis
     * @return
     */
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

    /**
     * 归还jedis 连接
     * @param objs
     */
    public static void close(Jedis... objs){
        for (Jedis obj : objs) {
            obj.close();
        }
    }

}
