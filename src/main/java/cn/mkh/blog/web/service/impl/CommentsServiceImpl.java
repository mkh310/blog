package cn.mkh.blog.web.service.impl;

import cn.mkh.blog.web.dao.CommentsDao;
import cn.mkh.blog.web.domain.Comment;
import cn.mkh.blog.web.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    CommentsDao commentsDao;
    @Override
    public List<Comment> findCommentsByBlogId(Integer blogId) {
        return commentsDao.findCommentsByBlogId(blogId);
    }

    @Override
    @Transactional
    public void addComment(Comment comment) {
        comment.setCreateTime(new Date());
        if(comment.getParentCommentId() == -1){
            comment.setParentCommentId(null);
        }
        if(comment.getFatherCommentId() == -1){
            comment.setFatherCommentId(null);
        }
        if(StringUtils.isEmpty(comment.getNickname())){
            comment.setCommentNickname(null);
        }
        commentsDao.addComment(comment);
    }
}
