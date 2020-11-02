package cn.mkh.blog.web.domain;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class Type {

    private Integer id;//主键

    @NotBlank(message = "请输入要添加的分类")
    private String name;//名字

    private Integer countBlog;//所属的博客个数

    //属于该类型的博客
    private List<Blog> blogs = new ArrayList<>();

    public Type() {
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
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countBlog=" + countBlog +
                ", blogs=" + blogs +
                '}';
    }
}
