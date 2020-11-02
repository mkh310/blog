package cn.mkh.blog.web.service.impl;

import cn.mkh.blog.web.dao.BlogDao;
import cn.mkh.blog.web.dao.CommentDao;
import cn.mkh.blog.web.dao.TagDao;
import cn.mkh.blog.web.domain.Blog;
import cn.mkh.blog.web.service.BlogAdminService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BlogAdminServiceImpl implements BlogAdminService {

    @Autowired
    BlogDao blogDao;

    @Autowired
    TagDao tagDao;

    @Autowired
    CommentDao commentDao;

    @Override
    public List<Blog> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return blogDao.findAll();
    }


    @Override
    @Transactional
    public Integer addBlog(Blog blog, List<Integer> tagIds) {
        blog.setUpdateTime(new Date());
        blog.setCreateTime(new Date());
        blogDao.addBlog(blog);
        Integer id = blog.getId();
        if(tagIds.size() != 0){
            tagDao.addTagToBlogs_Tags(id, tagIds);
        }
        return id;
    }

    @Override
    public Blog findBlogById(Integer id) {
        return blogDao.findById(id);
    }

    @Override
    @Transactional
    public void deleteBlog(Integer id) {
        commentDao.delCommentByBlogId(id);
        tagDao.delTagFromBlogTags(id);
        blogDao.deleteBlog(id);
    }

    @Override
    public List<Blog> findByCondition(String title, String typeId, boolean recommend, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        title = "%" + title + "%";
        return blogDao.findByCondition(title, typeId, recommend);
    }

    @Override
    @Transactional
    public Integer editBlog(Blog blog, List<Integer> tagIds) {
        blog.setUpdateTime(new Date());
        blogDao.updateBlog(blog);
        tagDao.delTagFromBlogTags(blog.getId());
        if(tagIds.size() != 0) {
            tagDao.addTagToBlogs_Tags(blog.getId(), tagIds);
        }
        return blog.getId();
    }

    @Override
    public List<Blog> findRecommendBlogs(int i) {
        return blogDao.findRecommendBlogs(i);
    }

    @Override
    public List<Blog> findBySearch(Integer pageNum, Integer pageSize, String content) {
        PageHelper.startPage(pageNum,pageSize);
        return blogDao.findBySearch("%"+content+"%");
    }

    @Override
    public List<Blog> findAllPublished(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return blogDao.findAllPublished();
    }


}
