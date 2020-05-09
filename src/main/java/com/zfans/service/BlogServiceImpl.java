package com.zfans.service;

import com.zfans.NotFoundException;
import com.zfans.dao.BlogRepository;
import com.zfans.entity.Blog;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Zfans
 * @date 2020/05/09 2:18
 */

/**
 * @ Service 注解，其实做了两件事情：
 * <p>
 * 1.声明 School.java 是一个 bean 。这点很重要，因为 School.java 是一个 bean，
 * 其他的类才可以使用 @Autowired 将 School 作为一个成员变量自动注入。
 * <p>
 * 2.School.java 在 bean 中的 id 是 "school"，即类名且首字母小写。
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogRepository blogRepository;

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, Blog blog) {
        return null;
    }

    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Optional<Blog> b = blogRepository.findById(id);
        if (!b.isPresent()) {
            throw new NotFoundException("该博客不存在！");
        }
        BeanUtils.copyProperties(blog, b.get());
        return blogRepository.save(b.get());
    }

    @Override
    public void deleteBlog(Long id) {

    }
}
