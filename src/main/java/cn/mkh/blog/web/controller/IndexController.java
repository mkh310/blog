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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    BlogAdminService blogAdminService;
    @Autowired
    TagsService tagsService;
    @Autowired
    TypesService typesService;

    @GetMapping("")
    public String toIndexPage(Model model,
                              @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = "3") Integer pageSize) {
        List<Blog> blogs = blogAdminService.findAll(pageNum, pageSize);
        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogs);
        List<Tag> tagsTop = tagsService.findTagsTop(7);
        List<Type> typeTop = typesService.findTypeTop(5);
        List<Blog> recommendBlogs = blogAdminService.findRecommendBlogs(5);
        model.addAttribute("pageInfo",blogPageInfo);
        model.addAttribute("recommendBlogs",recommendBlogs);
        model.addAttribute("tags",tagsTop);
        model.addAttribute("types",typeTop);
        return "index";
    }
}
