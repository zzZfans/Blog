package com.zfans.dao;

import com.zfans.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Zfans
 * @date 2020/05/07 13:46
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);
}
