package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;

import java.util.List;

public interface CategoryService {

    /**
     * 查询该表所有数据
     * @return
     */
    public List<Category> findAll();

    /**
     * 查询 对应的类别名
     * @return
     */
    public String findByCid(int cid);
}
