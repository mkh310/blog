package cn.mkh.blog.web.dao;

import cn.mkh.blog.web.domain.Blog;
import cn.mkh.blog.web.domain.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {
    @Select("select * from comment where b_blog = #{id}")
    public List<Comment> findByBlogId(Integer id);

    @Delete("delete  from comment where b_blog = #{id}")
    public void delCommentByBlogId(@Param("id") Integer id);
}
