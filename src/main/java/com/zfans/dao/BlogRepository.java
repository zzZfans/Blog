package com.zfans.dao;

import com.zfans.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Zfans
 * @date 2020/05/09 2:19
 */
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {

    @Query("select b from Blog b where b.recommend =true ")
    List<Blog> findTop(Pageable pageable);

    @Query("select b from Blog  b where  b.title like %?1% or b.description like %?1%")
    Page<Blog> findByQuery(String query, Pageable pageable);
}
