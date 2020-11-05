package cn.mkh.blog.web.dao;

import cn.mkh.blog.web.domain.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogDao {

    @Select("select * from blog ORDER BY updateTime DESC")
    @Results(id = "blog", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "content", property = "content"),
            @Result(column = "firstPicture", property = "firstPicture"),
            @Result(column = "flag", property = "flag"),
            @Result(column = "views", property = "views"),
            @Result(column = "appreciation", property = "appreciation"),
            @Result(column = "shareStatement", property = "shareStatement"),
            @Result(column = "commentabled", property = "commentabled"),
            @Result(column = "published", property = "published"),
            @Result(column = "recommend", property = "recommend"),
            @Result(column = "createTime", property = "createTime"),
            @Result(column = "updateTime", property = "updateTime"),
            @Result(column = "t_type", property = "type", javaType = Type.class, one = @One(select = "cn.mkh.blog.web.dao.TypeDao.findById")),
            //column指定用Blog表中的什么字段去查，property指定类中的属性，Many指定调用什么方法
            @Result(column = "id", property = "tags", javaType = List.class, many = @Many(select = "cn.mkh.blog.web.dao.TagDao.findByBlogId")),
            @Result(column = "u_user", property = "user", javaType = User.class, one = @One(select = "cn.mkh.blog.web.dao.UserDao.findUserById")),
            @Result(column = "id", property = "comments", javaType = List.class, many = @Many(select = "cn.mkh.blog.web.dao.CommentsDao.findByBlogId"))
    })
    List<Blog> findAll();




    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO blog (" +
            "  `title`," +
            "  `content`," +
            "  `firstPicture`," +
            "  `appreciation`," +
            "  `shareStatement`," +
            "  `commentabled`," +
            "  `published`," +
            "  `recommend`," +
            "  `createTime`," +
            "  `t_type`,"+
            "  `u_user`, "+
            "  `description`,"+
            "  updateTime,"+
            " flag"+
            ") " +
            "VALUES" +
            "(" +
            "#{title}," +
            "#{content}," +
            "#{firstPicture}," +
            "#{appreciation}," +
            "#{shareStatement}," +
            "#{commentabled}," +
            "#{published}," +
            "#{recommend}," +
            "#{createTime}," +
            "#{typeId}," +
            "#{user.id}," +
            "#{description}," +
            "#{updateTime},"+
            "#{flag}"+
            "  ) ;")
    void addBlog(Blog blog);


    @Select("select * from blog where id = #{id}")
    @ResultMap("blog")
    Blog findById(Integer id);

    @Update("UPDATE " +
            "blog " +
            "SET" +
            "  title = #{title}," +
            "  content = #{content}," +
            "  firstPicture = #{firstPicture}," +
            "  flag = #{flag}," +
            "  appreciation = #{appreciation}," +
            "  shareStatement = #{shareStatement}," +
            "  commentabled = #{commentabled}," +
            "  published = #{published}," +
            "  recommend = #{recommend}," +
            "  updateTime = #{updateTime}," +
            "  t_type = #{typeId}," +
            "  u_user = #{user.id}, " +
            "  description = #{description}"+
            "WHERE id = #{id} ;")
    void updateBlog(Blog blog);


    @Delete("delete from blog where id = #{id}")
    void deleteBlog(Integer id);

    @Select({"<script>",
            "select * from blog order by updateTime desc",
            "<where>",
            "<if test='title != null and title !=\"%%\" '>and title like #{title}</if>",
            "<if test='typeId != null and typeId !=\"\" '>and t_type = #{typeId}</if>",
            "<if test='recommend != false'>and recommend=1</if>",
            "</where>",
            "</script>"})
    @ResultMap("blog")
    List<Blog> findByCondition(String title, String typeId, boolean recommend);

    @Select("select * from blog where recommend = true and published = true ORDER BY updateTime DESC limit 0,#{i}")
    List<Blog> findRecommendBlogs(int i);

    @Select("SELECT  " +
            "b.* " +
            "FROM " +
            "  blog b,blogs_tags bt,type t,tag tg " +
            "WHERE " +
            "  b.`t_type`  = t.`id` AND tg.`id` = bt.`tag_id` AND b.`id` = bt.blog_id " +
            "AND CONCAT(b.`title`,b.`content`,b.`description`,t.`name`,tg.`name`) LIKE #{content} " +
            "and published = true " +
            "group by b.id"+
            " order by b.updateTime desc "
    )
    @ResultMap("blog")
    List<Blog> findBySearch(String content);

    @Select("select * from blog where published = true order by updateTime desc")
    @ResultMap("blog")
    List<Blog> findAllPublished();

    @Select("SELECT * FROM blog WHERE blog.`t_type` = #{typeId} and published = true  order by updateTime desc")
    @ResultMap("blog")
    List<Blog> findBlogsByTypeId(Integer typeId);

    @Update("UPDATE blog SET views = views+1 WHERE id = #{id}")
    void updateBlogViews(Integer id);

    @Select("SELECT b.* FROM blog b,blogs_tags bt WHERE bt.`blog_id` = b.id AND bt.`tag_id` = #{id} and published = true order by updateTime desc")
    @ResultMap("blog")
    List<Blog> findBlogsByTagId(Integer id);

    @Select("SELECT " +
            " DATE_FORMAT(b.`updateTime`,'%Y') YEAR " +
            " " +
            "FROM blog b " +
            " where published = true " +
            "GROUP BY  YEAR  " +
            "ORDER BY YEAR desc;")
    List<String> findGroupYear();

    @Select("SELECT * FROM blog b WHERE DATE_FORMAT(b.`updateTime`,'%Y') = #{year} and published = true ")
    @ResultMap("blog")
    List<Blog> findBlogByYear(String year);

    @Select("select count(id) from blog where published = true")
    Integer findBlogCount();
}
