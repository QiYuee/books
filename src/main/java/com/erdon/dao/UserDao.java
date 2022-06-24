package com.erdon.dao;

import com.erdon.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    public int updateUser(Object... args);
    List<User> queryUsers();
    User queryUser(Integer id);
    int insertUser(User user);
    public User login(@Param("username") String username,@Param("password") String password);
    User existsUserName(String username);

}
