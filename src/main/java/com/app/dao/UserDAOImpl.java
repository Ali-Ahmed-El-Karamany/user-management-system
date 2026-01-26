package com.app.dao;

import com.app.model.Role;
import com.app.model.User;
import com.app.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
