package web.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ProvinceDao;
import dao.impl.ProvinceDaoImpl;
import domain.Province;
import jedis.utils.JedisUtils;
import redis.clients.jedis.Jedis;
import web.service.ProvinceService;

import java.util.List;
import java.util.Map;

public class ProvinceServiceImpl implements ProvinceService {
    private final ProvinceDao provinceDao = new ProvinceDaoImpl();
    private final Jedis jedis = JedisUtils.getJedis();

    @Override
    public List<Province> findAllProvince() {
        return provinceDao.findAll();
    }

    @Override
    public String finaAllJson() {
        //1.查询redis 中是否有缓存
        String provinceJson = jedis.get("province");
        if(provinceJson == null || provinceJson.length()==0){
            //redis中无缓存,需要写入缓存
            System.out.println("redis 中无缓存，从数据库写入。。。");
            //从数据库中写入
            List<Province> list = provinceDao.findAll();
            ObjectMapper mapper = new ObjectMapper();

            try {
                provinceJson = mapper.writeValueAsString(list);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //写入redis 缓存中
            jedis.set("province",provinceJson);
        }else{
            //有缓存 读取
            System.out.println("redis 中有缓存，直接读取。。。");
        }

        return provinceJson;
    }
}
