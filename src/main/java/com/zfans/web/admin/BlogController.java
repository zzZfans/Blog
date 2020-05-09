package com.zfans.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Zfans
 * @date 2020/5/6 2:09
 */
@Controller
@RequestMapping("/admin")
public class BlogController {
    @GetMapping("/blogs")
    public String list() {
        return "admin/blogs";
    }
}
