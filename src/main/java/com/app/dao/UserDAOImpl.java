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
        if(email == null){
            throw new IllegalArgumentException("email cannot be null");
        }
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
}
