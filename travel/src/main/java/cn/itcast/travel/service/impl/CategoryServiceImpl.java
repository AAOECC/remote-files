package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao = new CategoryDaoImpl();

    //private List<Category> all = null;
    /**
     * 查询该表所有数据
     * @return
     */
    @Override
    public List<Category> findAll() {
        List<Category> cg = null;
        /*
        数据存入redis 缓存中，从redis中读取
         */
        Jedis jedis = JedisUtil.getJedis();
        Set<String> categorys = jedis.zrange("category", 0, -1);

        //1.判断 redis 缓存中是否有数据
        if (categorys == null || categorys.size() == 0) {
            //2.无数据，从数据库中读取
            cg = categoryDao.findAll();
            //3.将数据存入 redis 缓存中
            for (Category category : cg) {
                jedis.zadd("category",category.getCid(),category.getCname());
            }
        } else {
            //4.有数据，进行数据转换
            cg = new ArrayList<Category>();
            int i = 1;
            for (String cname:categorys){
                Category category = new Category();
                category.setCid(i++);
                category.setCname(cname);
                cg.add(category);
            }
        }
        return cg;
    }
}
