package cn.mkh.blog.web.controller.admin;

import cn.mkh.blog.web.domain.Blog;
import cn.mkh.blog.web.domain.Tag;
import cn.mkh.blog.web.domain.Type;
import cn.mkh.blog.web.domain.User;
import cn.mkh.blog.web.service.BlogAdminService;
import cn.mkh.blog.web.service.TagsService;
import cn.mkh.blog.web.service.TypesService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogAdminController {
    @Autowired
    BlogAdminService blogservice;
    @Autowired
    TypesService typesService;
    @Autowired
    TagsService tagsService;

    //前往博客管理页面
    @GetMapping("/blogs")
    public String blogs(Model model,
                        @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                        @RequestParam(required = false,defaultValue = "5") Integer pageSize){
       List<Blog> blos = blogservice.findAll(pageNum,pageSize);
       List<Type> allTypes = typesService.findAllTypes(1, 0);
        PageInfo pageinfo = new PageInfo(blos);
       model.addAttribute("pageInfo",pageinfo);
       model.addAttribute("types",allTypes);
        return "admin/blogs";
    }

    @GetMapping("/blog/types")
    @ResponseBody
    public List<Type> getType(){
        return typesService.findAllTypes(0,99);
    }


    //刷新博客列表
    @PostMapping("/blogs/search")
    public String blogsRefresh(@RequestParam(name = "title")String title,
                               @RequestParam(name = "typeId",required = false)String typeId,
                               @RequestParam(name = "recommend")boolean recommend,
                               @RequestParam(required = false,defaultValue = "1",name = "page")int pageNum,
                               @RequestParam(required = false,defaultValue = "5")int pageSize,
                               Model model){
        List<Blog> blogs = blogservice.findByCondition(title,typeId,recommend,pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogs::blogList";//只返回blos中的blogList片段
    }

    //前往博客增加控制器
    @GetMapping("/blog")
    public String toAddPage(Model model){
        model.addAttribute("blog",new Blog());
        List<Type> allTypes = typesService.findAllTypes(1, 0);
        List<Tag> allTags = tagsService.findAllTags(1, 0);
        model.addAttribute("types",allTypes);
        model.addAttribute("tags",allTags);
        return "admin/blogs-input";
    }

    //添加和修改Blog
    @PostMapping("/blog")
    public String addBlog(Blog blog, HttpSession session, @RequestParam(required = false,name = "tagIds") List<Integer> tagIds, RedirectAttributes redirectAttributes){
        blog.setUser((User) session.getAttribute("user"));
        Integer id = null;
        if(blog.getId() != null){
            id = blogservice.editBlog(blog,tagIds);
        }else {
            id = blogservice.addBlog(blog,tagIds);
        }
        if(id != null){
            redirectAttributes.addFlashAttribute("message","操作成功");
        }else {
            redirectAttributes.addFlashAttribute("message","操作失败");
        }
        return "redirect:/admin/blogs";
    }

    //去修改页面
    @GetMapping("/blog/{id}")
    public String toEditPage(@PathVariable Integer id,Model model){
        Blog blog = blogservice.findBlogById(id);
        List<Type> allTypes = typesService.findAllTypes(1, 0);
        List<Tag> allTags = tagsService.findAllTags(1, 0);
        model.addAttribute("types",allTypes);
        model.addAttribute("tags",allTags);
        //给页面返回博客的tagsId
        List<Tag> tags = blog.getTags();
        model.addAttribute("tagIds",tagsToIds(tags));
        model.addAttribute("blog",blog);
        return "admin/blogs-input";
    }


    //删除博客
    @GetMapping("/blog/{id}/delete")
    @ResponseBody
    public String delBolg(@PathVariable("id") Integer id){
        blogservice.deleteBlog(id);
        return "删除成功";
    }


    //给页面返回tagIds
    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }else {
            return "";
        }
    }
}
