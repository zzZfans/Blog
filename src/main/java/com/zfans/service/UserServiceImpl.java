package com.zfans.service;

import com.zfans.dao.UserRepository;
import com.zfans.entity.User;
import com.zfans.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zfans
 * @date 2020/5/5 14:23
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
