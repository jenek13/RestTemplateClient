package com.ten.service;

import com.ten.model.User;
import com.ten.restTemplate.MyRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    public UserServiceImpl() {
    }

    @Autowired
    private MyRestTemplate myRestTemplate;
    //private UserDAO userDAO;

    @Override
    public List<User> listUser() {
        return myRestTemplate.selectAllUsers();
    }



//    @Override
//    public User selectUser(long id)  {
//        try {
//            return  userDAO.getUserById(id);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return  null;
//    }
//
//
//

//
//    @Override
//    public void insertUser(User user) {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        userDAO.addUser(user);
//    }
//
//    @Override
//    public void updateUser(User user)  {
//        try {
//            userDAO.editUser(user);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
    @Override
    public User getUserByLogin(String login) {
        return myRestTemplate.getUserByLogin(login);
    }

    @Override
    public void deleteUser(long id)  {
        myRestTemplate.removeUser(id);
    }

}
