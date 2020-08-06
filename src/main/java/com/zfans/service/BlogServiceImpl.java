package com.zfans.service;

import com.zfans.NotFoundException;
import com.zfans.dao.BlogRepository;
import com.zfans.entity.Blog;
import com.zfans.entity.Type;
import com.zfans.util.MarkdownUtils;
import com.zfans.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private BlogRepository blogRepository;

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    @Override
    public Blog getAndConvertBlog(Long id) {

        Blog blog = blogRepository.getOne(id);

        if (blog == null) {
            throw new NotFoundException("该博客不存在！");
        }

        String content = blog.getContent();

        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));

        return blog;
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blogQuery) {
        return blogRepository.findAll((Specification<Blog>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!"".equals(blogQuery.getTitle()) && blogQuery.getTitle() != null) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + blogQuery.getTitle() + "%"));
            }
            if (blogQuery.getTypeId() != null) {
                predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"), blogQuery.getTypeId()));
            }
            if (blogQuery.isRecommend()) {
                predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"), blogQuery.isRecommend()));
            }
            criteriaQuery.where(predicates.toArray(new Predicate[0]));
            return null;
        }, pageable);
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {

        if (blog.getId() == null) {
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        } else {
            blog.setCreateTime(new Date(blog.getCreateTimeL()));
            blog.setUpdateTime(new Date());
        }

        return blogRepository.save(blog);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }


    @Override
    public List<Blog> listBlogTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(0, size, sort);

        return blogRepository.findTop(pageable);
    }

    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return blogRepository.findByQuery(query, pageable);
    }
}
