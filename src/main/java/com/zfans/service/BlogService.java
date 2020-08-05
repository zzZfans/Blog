package com.zfans.service;

import com.zfans.entity.Blog;
import com.zfans.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Zfans
 * @date 2020/05/09 2:15
 */
public interface BlogService {
    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blogQuery);

    Page<Blog> listBlog(Pageable pageable);

    List<Blog> listBlogTop(Integer size);

    Blog saveBlog(Blog blog);

    void deleteBlog(Long id);

}
