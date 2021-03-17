package usercase.service;

import usercase.domain.Admin;

/**
 * 登录业务 服务
 */
public interface LoginService {

    /**
     *
     * @return
     */
    public boolean isAdmin(Admin loginAdmin);
}
