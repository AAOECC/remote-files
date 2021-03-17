package service;

import org.junit.Test;
import usercase.dao.AdminDao;
import usercase.dao.UserDao;
import usercase.dao.impl.UserDaoImpl;
import usercase.dao.impl.AdminDaoImpl;
import usercase.domain.Admin;
import usercase.service.LoginService;
import usercase.service.impl.LoginServiceImpl;

public class TestAdminDaoImpl {

    @Test
    public void test_getAdmin(){
        Admin loginAdmin = new Admin();

        loginAdmin.setName("admin");
        loginAdmin.setPassword("123456");

        LoginService loginService = new LoginServiceImpl();
        System.out.println(loginService.isAdmin(loginAdmin));
    }
}
