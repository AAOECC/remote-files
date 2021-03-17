package usercase.dao;

import usercase.domain.Admin;

public interface AdminDao {

    /**
     * 查询数据库中是否有传入的admin
     * 有则返回 数据库中的数据
     * @param loginAdmin
     * @return
     */
    public Admin getAdmin(Admin loginAdmin);
}
