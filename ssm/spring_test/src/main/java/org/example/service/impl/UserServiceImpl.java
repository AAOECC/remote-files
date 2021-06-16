package org.example.service.impl;

import org.example.dao.UserDao;
import org.example.domain.Role;
import org.example.domain.User;
import org.example.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 查询 user 完整列表
     *
     * @return user 完整列表
     */
    @Override
    public List<User> list() {
        long startTime = System.currentTimeMillis();
        //查询 user 表中包含信息
        List<User> userList = userDao.findAll();
        long firstTime = System.currentTimeMillis();
        System.out.println("UserService.list() 数据库总 user 查询 in " + (firstTime - startTime) + "ms ....");

        //查询 添加 Roles 信息
        int count = 0;
        for (User user : userList) {
            Long userId = user.getId();
            List<Role> roles = userDao.findRoles(userId);
            user.setRoles(roles);
            count++;
        }
        long endTimes = System.currentTimeMillis();
        System.out.println("UserService.list() 数据库多 user 查询 "+count+" 次 in " + (endTimes - firstTime) + "ms ....");
        return userList;
    }

    @Override
    @Transactional
    public void save(User user, Long[] roleIds) {
        Long userId = userDao.saveUserMsg(user);
        if (roleIds.length != 0){
            userDao.saveU_RMsg(userId,roleIds);
        }
    }

    @Override
    @Transactional
    public void del(Long userId) {
        //由于外键约束 ， 首先进行 User_role 表删除
        userDao.delFromU_R(userId);
        userDao.delFromUser(userId);
    }
}
