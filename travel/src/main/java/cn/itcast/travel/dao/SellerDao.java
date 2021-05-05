package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

public interface SellerDao {

    /**
     * 查找 seller
     * @param sid
     * @return
     */
    public Seller findBySid(int sid);
}
