package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteDao favoriteDao = new FavoriteDaoImpl();

    /**
     * 判断用户是否收藏该路线
     * @param rid 线路id
     * @param uid 用户id
     * @return True OR False
     */
    @Override
    public boolean isFavorite(int rid, int uid) {
        Favorite favorite = favoriteDao.isFavorite(rid, uid);

        return favorite != null;
    }

    @Override
    public int favoriteCount(int rid) {
        return favoriteDao.favoriteCount(rid);
    }

    @Override
    public void addFavorite(int rid, int uid) {
        favoriteDao.addFavorite(rid, uid);
    }
}
