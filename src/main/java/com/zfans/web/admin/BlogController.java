package com.zfans.web.admin;

import com.zfans.entity.Blog;
import com.zfans.entity.User;
import com.zfans.service.BlogService;
import com.zfans.service.TagService;
import com.zfans.service.TypeService;
import com.zfans.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


/**
 * @author Zfans
 * @date 2020/5/6 2:09
 */
@Controller
@RequestMapping("/admin")
public class BlogController {
    private static final String INPUT = "admin/blog-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String list(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                       BlogQuery blogQuery, Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        return LIST;
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         BlogQuery blogQuery,
                         Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        return "admin/blogs :: blogList";
    }


    @GetMapping("blogs/input")
    public String input(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        model.addAttribute("blog", new Blog());
        return INPUT;
    }

    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getTypeById(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b = blogService.saveBlog(blog);
        if (b == null) {
            attributes.addFlashAttribute("message", "操作失败！");
        } else {
            attributes.addFlashAttribute("message", "操作成功！");
        }
        return REDIRECT_LIST;
    }
}
