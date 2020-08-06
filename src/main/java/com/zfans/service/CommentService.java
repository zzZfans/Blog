package com.zfans.service;

import com.zfans.entity.Comment;

import java.util.List;

/**
 * @author Zfans
 * @date 2020/08/07 1:45
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);

}
