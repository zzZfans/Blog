package com.zfans.service;

import com.zfans.entity.User;

/**
 * @author Zfans
 * @date 2020/5/5 14:22
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
