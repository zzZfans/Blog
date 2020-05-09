package com.zfans.dao;

import com.zfans.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Zfans
 * @date 2020/05/09 2:19
 */
public interface BlogRepository extends JpaRepository<Blog, Long> {
}
