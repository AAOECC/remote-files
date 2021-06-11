package org.example.dao;

import org.example.domain.Role;

import java.util.List;

public interface RoleDao {

    public List<Role> findAll();

    public void save(Role role);
}
