package cn.mkh.blog.web.dao;

import cn.mkh.blog.web.domain.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsDao {

    @Select("select * from comment where blog_id = #{blogId} and parentCommentId is null order by createTime asc")
    @Results(id = "comment",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "id",property = "id"),
            @Result(column = "nickname",property = "nickname"),
            @Result(column = "email",property = "email"),
            @Result(column = "content",property = "content"),
            @Result(column = "avatar",property = "avatar"),
            @Result(column = "createTime",property = "createTime"),
            @Result(column = "createTime",property = "createTime"),
            @Result(column = "blog_id",property = "blogId"),
            @Result(column = "id",property = "replyComments",javaType = List.class,many = @Many(select = "cn.mkh.blog.web.dao.CommentsDao.findReplyCommentsById")
/*            @Result(column = "parentCommentId",property = "parentComment",javaType = List.class,one = @One(select = "cn.mkh.blog.web.dao.CommentsDao.findParentComment")*/)
    })
    List<Comment> findCommentsByBlogId(Integer blogId);

    @Select("select * from comment where fatherCommentId = #{id}")
    List<Comment> findReplyCommentsById(Integer id);

    @Select("select * from comment where id = #{parentCommentId}")
    Comment findParentComment(Integer parentCommentId);

    @Insert("INSERT INTO `comment` ( " +
            "  `nickname`, " +
            "  `email`, " +
            "  `content`, " +
            "  `avatar`, " +
            "  `createTime`, " +
            "  `parentCommentId`, " +
            "  `blog_id`, " +
            "  `fatherCommentId`, " +
            " commentNickname, "+
            " adminComment "+
            ") " +
            "VALUES " +
            "  ( " +
            "    #{nickname}, " +
            "    #{email}, " +
            "    #{content}, " +
            "    #{avatar}, " +
            "    #{createTime}, " +
            "    #{parentCommentId}, " +
            "    #{blogId}, " +
            "    #{fatherCommentId}, " +
            " #{commentNickname}, "+
            " #{adminComment} "+
            "  );")
    void addComment(Comment comment);
}
