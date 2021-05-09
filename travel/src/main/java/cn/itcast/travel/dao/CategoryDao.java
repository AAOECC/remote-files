package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;

import java.util.List;

public interface CategoryDao {
    /**
     * 查找数据库中所有的值
     */
    public List<Category> findAll();
}
