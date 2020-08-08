package com.zfans.dao;

import com.zfans.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Zfans
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * @param userName 用户名
     * @param password 密码
     * @return User
     */
    User findByUserNameAndPassword(String userName, String password);
}
