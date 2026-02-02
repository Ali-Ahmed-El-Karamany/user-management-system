package com.app.dao;

import com.app.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

     User getUserByEmail(String email) throws SQLException;
     User getUserById(int id) throws SQLException;
     boolean isEmailExists(String email) throws SQLException;
     void registerUser(User user) throws SQLException;
     List<User> getAllUsers() throws SQLException;
     boolean deleteUser(int id) throws SQLException;
     boolean updateProfile(int userId, String newName, String newEmail) throws SQLException;
     boolean updatePassword(int id, String hashedPassword) throws SQLException;


}
