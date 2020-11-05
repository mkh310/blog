package cn.mkh.blog.web.controller;

import cn.mkh.blog.web.domain.Blog;
import cn.mkh.blog.web.domain.Tag;
import cn.mkh.blog.web.domain.Type;
import cn.mkh.blog.web.service.BlogAdminService;
import cn.mkh.blog.web.service.TagsService;
import cn.mkh.blog.web.service.TypesService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagShowController {


    @Autowired
    TagsService tagsService;

    @Autowired
    BlogAdminService blogAdminService;

    @GetMapping("/tags/{id}/{pageNum}")
    public String toTypePage(Model model,
                             @PathVariable("pageNum") Integer pageNum,
                             @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                             @PathVariable("id") Integer id){
        List<Tag> tagsTop = tagsService.findTagsTop(100);
        if(id == -1){
            id = tagsTop.get(0).getId();
        }
        List<Blog> blogs = blogAdminService.findBlogByTagId(pageNum,pageSize,id);
        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogs);
        model.addAttribute("tags",tagsTop);
        model.addAttribute("pageInfo",blogPageInfo);
        model.addAttribute("activeId",id);
        return "tags";
    }
}
