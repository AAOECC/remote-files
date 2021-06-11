package org.example.dao;

import org.example.domain.Role;
import org.example.domain.User;

import java.util.List;

public interface UserDao {

    public List<User> findAll();

    /**
     * 根据 userId 联立两表 查询 Roles 信息
     * @param userId 用户id
     * @return 用户角色列表
     */
    public List<Role> findRoles(Long userId);
}
