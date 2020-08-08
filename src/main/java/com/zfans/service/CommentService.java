package com.zfans.service;

import com.zfans.entity.Comment;

import java.util.List;

/**
 * @author Zfans
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);

}
