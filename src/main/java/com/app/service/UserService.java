package com.app.service;

import com.app.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    User authenticate(String email, String password) throws SQLException;
    User getUserById(int id) throws SQLException;
    boolean register(String name, String email, String password) throws SQLException;
    List<User> getAllUsers() throws SQLException;
    void deleteUser(int userId, int adminId) throws SQLException, IllegalStateException;
    boolean updateProfile(int userId, String newName, String newEmail) throws SQLException, IllegalStateException, SecurityException;

}
