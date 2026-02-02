package com.app.service;

import com.app.controller.admin.DeleteUserServlet;
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
    public User getUserById(int id) throws SQLException {
        return userDAO.getUserById(id);
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

    @Override
    public boolean deleteUser(int userId, int adminId) throws SQLException, IllegalStateException  {
        if(userId == adminId){
            throw new IllegalStateException("Self-deletion is not permitted");
        }

        return userDAO.deleteUser(userId);
    }

    @Override
    public boolean updateProfile(int userId, String newName, String newEmail) throws SQLException, IllegalStateException, SecurityException {
        User user = null;
        String oldEmail;

        user = userDAO.getUserById(userId);

        if (user == null)
            throw new SecurityException("User not found");

        oldEmail = user.getEmail();

        if(!newEmail.equals(oldEmail))
        {
            if(userDAO.isEmailExists(newEmail))
            {
                throw new IllegalStateException("Email Already exists, please try another email");
            }
        }

        return userDAO.updateProfile(userId, newName, newEmail);
    }

    @Override
    public boolean confirmPassword(String newPassword, String confirmPassword) {
        return newPassword.equals(confirmPassword);

    }

    @Override
    public boolean changePassword(User loggedUser, String oldPassword, String newPassword) throws SQLException, IllegalArgumentException,
            IllegalStateException, SecurityException {
        if(newPassword.equals(oldPassword))
            throw new IllegalArgumentException("New password must be different from the current password");

        User user = getUserById(loggedUser.getId());

        if (user == null)
            throw new SecurityException("User not found");

        if(PasswordUtil.verifyPassword(oldPassword, user.getPassword()))
        {
            String hashedNewPassword= PasswordUtil.hashPassword(newPassword);
            return userDAO.updatePassword(loggedUser.getId(),hashedNewPassword);
        }
        else
            throw new IllegalStateException("Current password is incorrect");
    }
}
