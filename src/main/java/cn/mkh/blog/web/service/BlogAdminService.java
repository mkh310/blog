package cn.mkh.blog.web.service;

import cn.mkh.blog.web.domain.Blog;

import java.util.List;

public interface BlogAdminService {
    List<Blog> findAll(Integer pageNum, Integer pageSize);

    Integer addBlog(Blog blog, List<Integer> tagIds);

    Blog findBlogById(Integer id);

    void deleteBlog(Integer id);

    List<Blog> findByCondition(String title, String typeId, boolean recommend, int page, int pageSize);

    Integer editBlog(Blog blog, List<Integer> tagIds);

    List<Blog> findRecommendBlogs(int i);

    //通过搜索查找
    List<Blog> findBySearch(Integer pageNum, Integer pageSize, String content);


    List<Blog> findAllPublished(Integer pageNum, Integer pageSize);
}
