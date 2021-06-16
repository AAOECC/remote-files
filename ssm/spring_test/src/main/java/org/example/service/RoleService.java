package org.example.service;

import org.example.domain.Role;

import java.util.List;

public interface RoleService {

    public List<Role> list();

    public void save(Role role);
}
