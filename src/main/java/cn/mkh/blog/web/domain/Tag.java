package cn.mkh.blog.web.domain;

import java.util.ArrayList;
import java.util.List;

public class Tag {

    private Integer id;//主键
    private String name;//名称
    private Integer countBlog;//所属的博客数量

    //有该标签的博客
    private List<Blog> blogs = new ArrayList<>();

    public Tag() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public Integer getCountBlog() {
        return countBlog;
    }

    public void setCountBlog(Integer countBlog) {
        this.countBlog = countBlog;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countBlog=" + countBlog +
                ", blogs=" + blogs +
                '}';
    }
}
