package com.app.dao;

import com.app.model.Role;
import com.app.model.User;
import com.app.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO{
    @Override
    public User getUserByEmail(String email) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM users WHERE email=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pSt = conn.prepareStatement(sql)) {
            pSt.setString(1, email);

            ResultSet rs= pSt.executeQuery();
            if(rs.next())
            {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(Role.valueOf(rs.getString("role")));
                user.setRegistrationTime(rs.getTimestamp("created_at"));

            }
        }
        return user;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM users WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pSt = conn.prepareStatement(sql)) {
            pSt.setInt(1, id);

            ResultSet rs= pSt.executeQuery();
            if(rs.next())
            {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(Role.valueOf(rs.getString("role")));
                user.setRegistrationTime(rs.getTimestamp("created_at"));
            }
        }
        return user;
    }

    @Override
    public boolean isEmailExists(String email) throws SQLException {
        String sql ="SELECT 1 FROM users WHERE email=?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pSt = conn.prepareStatement(sql)){

            pSt.setString(1,email);
            ResultSet rs = pSt.executeQuery();
            return rs.next();
        }
    }

    @Override
    public void registerUser(User user) throws SQLException {
        String sql =
                "INSERT INTO users(name, email, password, role) " +
                        "VALUES (?, ?, ?, ?);";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pSt = conn.prepareStatement(sql)){

            pSt.setString(1,user.getName());
            pSt.setString(2,user.getEmail());
            pSt.setString(3,user.getPassword());
            pSt.setString(4,user.getRole().name());

            pSt.executeUpdate();
        }
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        String sql = "SELECT id, name, email, role, created_at FROM users;";
        List<User> users = new ArrayList<>();
        User user;

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pSt = conn.prepareStatement(sql);
             ResultSet rs = pSt.executeQuery();) {

            while(rs.next()){
                user = new User();

                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setRole(Role.valueOf(rs.getString("role")));
                user.setRegistrationTime(rs.getTimestamp("created_at"));

                users.add(user);
            }
        }
        return users;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id=?";
        int affected;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pSt = conn.prepareStatement(sql)) {

            pSt.setInt(1, id);
            affected = pSt.executeUpdate();
        }
        return affected > 0;
    }

    @Override
    public boolean updateProfile(int userId, String newName, String newEmail) throws SQLException {
        String sql = "UPDATE users SET name=?, email=? WHERE id =?";
        int affected;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pSt = conn.prepareStatement(sql)) {

            pSt.setString(1, newName);
            pSt.setString(2, newEmail);
            pSt.setInt(3, userId);

            affected = pSt.executeUpdate();
            return affected > 0 ;
        }
    }

    @Override
    public boolean updatePassword(int userId, String hashedPassword) throws SQLException{
        String sql = "UPDATE users SET password=? WHERE id=?";
        int affected;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pSt = conn.prepareStatement(sql)) {

            pSt.setString(1, hashedPassword);
            pSt.setInt(2, userId);

            affected = pSt.executeUpdate();
            return affected > 0 ;
        }
    }
}
