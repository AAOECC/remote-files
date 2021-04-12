package dao;

import domain.Province;

import java.util.List;

public interface ProvinceDao {

    /**
     * 查询 province 表中所有数据
     * @return
     */
    public List<Province> findAll();
}
