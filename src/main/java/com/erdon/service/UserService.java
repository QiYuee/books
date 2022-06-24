package com.erdon.service;

import com.erdon.entity.User;

import java.util.List;

public interface UserService {
    public int updateUser();
    public int registerUser(User user);
    Boolean existsUser(String username);
    public List<User> queryUsers();
    public User queryUser(Integer id);
    public int addUser(User user);
    public User login(User user);
}
