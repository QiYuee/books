package com.erdon.service.impl;

import com.erdon.dao.UserDao;
import com.erdon.entity.User;
import com.erdon.service.UserService;
import com.erdon.utils.MbUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao  userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int updateUser() {
//        String sql = "";
//        int updata = userDao.updateUser();
        return 0;
    }

    @Override
    public int registerUser(User user) {
     //    userDao = MbUtils.getSqlSession().getMapper(UserDao.class);
        int i = userDao.insertUser(user);
        return i;
    }

    @Override
    public Boolean existsUser(String username) {
      //  userDao = MbUtils.getSqlSession().getMapper(UserDao.class);
        User user = userDao.existsUserName(username);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<User> queryUsers() {
     //   userDao = MbUtils.getSqlSession().getMapper(UserDao.class);
        List<User> users = userDao.queryUsers();
        return users;
    }

    @Override
    public User queryUser(Integer id) {
     //   userDao = MbUtils.getSqlSession().getMapper(UserDao.class);
        User user = userDao.queryUser(id);
        return user;
    }

    @Override
    public int addUser(User user) {
      //  userDao = MbUtils.getSqlSession().getMapper(UserDao.class);
        int i = userDao.insertUser(user);
        return i;
    }

    @Override
    public User login(User user) {
     //   userDao = MbUtils.getSqlSession().getMapper(UserDao.class);
        User login = userDao.login(user.getUsername(), user.getPassword());
       return login;
    }
}
