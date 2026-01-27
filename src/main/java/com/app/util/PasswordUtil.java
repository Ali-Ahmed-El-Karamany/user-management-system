package com.app.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    private static final int  WORK_FACTOR =12;

    private PasswordUtil(){
        // Prevent object instantiation
    }

    public static String hashPassword(String password){
        String salt = BCrypt.gensalt(WORK_FACTOR);

        return BCrypt.hashpw(password, salt);
    }

    public static boolean verifyPassword(String password, String storedHash){

        return BCrypt.checkpw(password, storedHash);
    }
}
