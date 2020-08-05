package com.zfans.dao;

import com.zfans.entity.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Zfans
 * @date 2020/05/07 13:46
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);

    @Query("select t from Tag  t")
    List<Tag> findTag(Pageable pageable);
}
