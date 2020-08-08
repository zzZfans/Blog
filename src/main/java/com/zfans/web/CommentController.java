package com.zfans.web;

import com.zfans.entity.Comment;
import com.zfans.entity.User;
import com.zfans.service.BlogService;
import com.zfans.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @author Zfans
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
    public String post(Comment comment, HttpSession session) {

        Long blogId = comment.getBlog().getId();

        comment.setBlog(blogService.getBlog(blogId));

        User user = (User) session.getAttribute("user");

        if (user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        } else {
            comment.setAvatar(avatarUrl);
        }

        commentService.saveComment(comment);

        return "redirect:/comments/" + blogId;
    }
}
