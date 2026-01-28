package com.app.service;

import com.app.model.User;

import java.sql.SQLException;

public interface UserService {
    public User authenticate(String email, String password) throws SQLException;
    public boolean register(String name, String email, String password) throws SQLException;
}
