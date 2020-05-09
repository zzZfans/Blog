package com.zfans.dao;

import com.zfans.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Zfans
 * @date 2020/5/5 14:24
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * @param userName 用户名
     * @param password 密码
     * @return User
     */
    User findByUserNameAndPassword(String userName, String password);
}
