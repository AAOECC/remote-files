package cn.itcast.travel.service;

public interface FavoriteService {

    /**
     * 判断用户是否收藏该路线
     * @param rid 线路id
     * @param uid 用户id
     * @return True OR False
     */
    public boolean isFavorite(int rid, int uid);

    public int favoriteCount(int rid);

    public void addFavorite(int rid, int uid);
}
