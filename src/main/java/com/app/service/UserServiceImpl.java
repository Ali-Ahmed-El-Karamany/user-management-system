package com.app.service;

import com.app.dao.UserDAO;
import com.app.dao.UserDAOImpl;
import com.app.model.Role;
import com.app.model.User;
import com.app.util.PasswordUtil;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService{
    private final UserDAO userDAO;

    public UserServiceImpl(){
        userDAO = new UserDAOImpl();
    }

    @Override
    public User authenticate(String email, String password) throws SQLException {
       User user =  userDAO.getUserByEmail(email);

       if (user == null || !PasswordUtil.verifyPassword(password, user.getPassword()))
           return null;
       else
           return user;
    }

    @Override
    public boolean register(String name, String email, String password) throws SQLException {
        if(userDAO.isEmailExists(email)){
            return false;
        }
        else{
            String hashedPassword = PasswordUtil.hashPassword(password);
            User user = new User();

            user.setName(name);
            user.setEmail(email);
            user.setPassword(hashedPassword);
            user.setRole(Role.USER);
            userDAO.registerUser(user);

            return true;
        }
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }
}
