package cn.mkh.blog.web.service;

import cn.mkh.blog.web.domain.Comment;

import java.util.List;

public interface CommentsService {
    List<Comment> findCommentsByBlogId(Integer blogId);

    void addComment(Comment comment);
}
