package usercase.service.impl;

import usercase.dao.AdminDao;
import usercase.dao.impl.AdminDaoImpl;
import usercase.domain.Admin;
import usercase.service.LoginService;

public class LoginServiceImpl implements LoginService {


    @Override
    public boolean isAdmin(Admin loginAdmin) {
        AdminDao adminDao = new AdminDaoImpl();

        Admin admin = adminDao.getAdmin(loginAdmin);

        return admin != null;
    }
}
