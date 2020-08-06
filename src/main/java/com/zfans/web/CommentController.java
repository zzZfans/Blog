package com.zfans.web;

import com.zfans.entity.Comment;
import com.zfans.service.BlogService;
import com.zfans.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Zfans
 * @date 2020/08/07 1:40
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatarUrl;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {

        model.addAttribute("comments", commentService.listCommentByBlogId(blogId));

        return "blog::commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment) {

        Long blogId = comment.getBlog().getId();

        comment.setBlog(blogService.getBlog(blogId));

        comment.setAvatar(avatarUrl);

        commentService.saveComment(comment);

        return "redirect:/comments/" + blogId;
    }
}
