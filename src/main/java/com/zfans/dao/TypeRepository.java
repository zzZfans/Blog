package com.zfans.dao;

import com.zfans.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Zfans
 * @date 2020/05/06 19:29
 */
public interface TypeRepository extends JpaRepository<Type, Long> {
    Type findByName(String name);
}
