package com.zfans.service;

import com.zfans.entity.Blog;
import com.zfans.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author Zfans
 */
public interface BlogService {
    Blog getBlog(Long id);

    Blog getAndConvertBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blogQuery);

    Page<Blog> listBlog(Long tagId, Pageable pageable);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(String query, Pageable pageable);

    List<Blog> listBlogTop(Integer size);

    Map<String, List<Blog>> archiveBlog();

    Blog saveBlog(Blog blog);

    void deleteBlog(Long id);

    Long countBlog();

}
