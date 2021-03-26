package com.zfans.service.impl;

import com.zfans.dao.UserRepository;
import com.zfans.entity.User;
import com.zfans.service.UserService;
import com.zfans.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zfans
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String userName, String password) {
        return userRepository.findByUserNameAndPassword(userName, Md5Utils.code(password));
    }
}
