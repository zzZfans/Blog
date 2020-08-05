package com.zfans.service;

import com.zfans.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Zfans
 * @date 2020/05/07 13:44
 */
public interface TagService {
    Tag saveTag(Tag tag);

    Tag getTagById(Long id);

    Page<Tag> listTag(Pageable pageable);

    void deleteTagById(Long id);

    Tag getTagByName(String name);

    List<Tag> listTag();

    List<Tag> listTag(String ids);

    List<Tag> listTagTop(Integer size);
}
