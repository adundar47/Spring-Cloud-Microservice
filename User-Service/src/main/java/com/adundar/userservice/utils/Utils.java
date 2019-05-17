package com.adundar.userservice.utils;

import java.text.MessageFormat;

public class Utils {

    public static final String USER_NAME_NOT_FOUND           = "User not found. UserName: ''{0}''";

    public static final String USER_ID_NOT_FOUND             = "User not found. UserId: ''{0}''";

    public static final String USER_NAME_ALREADY_EXIST       = "User name already exist. UserName: ''{0}''";

    public static final String ALPHANUMERIC_UNDERSCORE_REGEX = "[a-zA-Z]{1,1}[a-zA-Z0-9_]{0,35}";

    public static final String USER_NAME_CHANGE_ERROR        = "User name of existing users can not be changed.";

    public static String getUserNameNotFoundError(String userName) {
        return MessageFormat.format(USER_NAME_NOT_FOUND, userName);
    }

    public static String getUserIdNotFoundError(String userId) {
        return MessageFormat.format(USER_ID_NOT_FOUND, userId);
    }

    public static String getUserNameAlreadyExistError(String userName) {
        return MessageFormat.format(USER_NAME_ALREADY_EXIST, userName);
    }
}
