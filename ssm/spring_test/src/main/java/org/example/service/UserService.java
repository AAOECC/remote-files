package org.example.service;

import org.example.domain.User;

import java.util.List;

public interface UserService {

    public List<User> list();

    public void save(User user, Long[] roleIds);

    public void del(Long userId);
}
