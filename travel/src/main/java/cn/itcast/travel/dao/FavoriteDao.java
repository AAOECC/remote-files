package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

public interface FavoriteDao {


    public Favorite isFavorite(int rid, int uid);

    public int favoriteCount(int rid);

    public void addFavorite(int rid, int uid);
}
