package com.app.dao;

import com.app.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

     User getUserByEmail(String email) throws SQLException;
}
