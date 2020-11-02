package cn.mkh.blog.web.dao;

import cn.mkh.blog.web.domain.Tag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagDao {
    @Select("select * from tag")
    public List<Tag> findAll();

    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    @Insert("insert into tag(name) values(#{name})")
    int addTag(Tag tag);

    @Select("select * from tag where name = #{name}")
    Tag findByName(Tag tag);

    @Delete("delete from tag where id = #{id}")
    void delTag(Integer id);

    @Update("update tag set name = #{name} where id = #{id}")
    Integer updaTag(Tag tag);

    @Select("select * from tag where id = #{id}")
    Tag findById(Integer id);

    @Select("select * from tag where id in(select tag_id from blogs_tags where blog_id = #{id})")
    List<Tag> findByBlogId(Integer id);

    @Insert("<script>"+
            "insert into blogs_tags(blog_id,tag_id) values "+
            "<foreach collection=\"tagIds\" item=\"tagId\" index=\"index\" separator=\",\">"+
            "(#{id},#{tagId})"+
            "</foreach>"+
            "</script>")
    void addTagToBlogs_Tags(@Param("id") Integer id,@Param("tagIds") List<Integer> tagIds);

    @Delete("delete from blogs_tags where blog_id=#{id}")
    void delTagFromBlogTags(@Param("id") Integer id);

    @Select("SELECT " +
            "t.`id`,t.`name`,IFNULL(COUNT(b.`tag_id`),0) countBlog " +
            "FROM " +
            "tag t " +
            "LEFT JOIN " +
            "blogs_tags b " +
            "ON " +
            "t.`id` = b.`tag_id` " +
            "GROUP BY t.`id` " +
            "ORDER BY countBlog DESC " +
            "LIMIT 0,#{i}; ")
    List<Tag> findTagsTop(int i);
}
