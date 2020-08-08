package com.zfans.service;

import com.zfans.entity.User;

/**
 * @author Zfans
 */
public interface UserService {
    /**
     * login check
     *
     * @param userName 用户名
     * @param password 密码
     * @return User
     */
    User checkUser(String userName, String password);
}
