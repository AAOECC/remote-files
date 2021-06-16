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

    /**
     * 保存用户信息到 User 表
     * @param user
     */
    public Long saveUserMsg(User user);

    /**
     * 保存用户角色关系到 User_Role 表
     * @param userId
     * @param roleIds
     */
    public void saveU_RMsg(Long userId, Long[] roleIds);

    /**
     * 从User_Role 表中删除数据
     * @param userId
     */
    public void delFromU_R(long userId);

    /**
     * 从 User 表中删除数据
     * @param userId
     */
    public void delFromUser(long userId);
}
