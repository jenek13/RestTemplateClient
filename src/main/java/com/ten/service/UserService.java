package com.ten.service;

import com.ten.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService<T> {


    List<User> listUser();
//    User selectUser(long id) ;
    void deleteUser(long id) ;
//    void insertUser(User user);
//    void updateUser(User user) ;
    User getUserByLogin(String login);
}
