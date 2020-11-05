package cn.mkh.blog.web.controller;

import cn.mkh.blog.web.domain.Blog;
import cn.mkh.blog.web.service.BlogAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ArchivesController {

    @Autowired
    BlogAdminService blogAdminService;

    @GetMapping("/archives")
    public String toArchivesPage(Model model){
        model.addAttribute("map",blogAdminService.findBlogsArchives());
        model.addAttribute("count",blogAdminService.findBlogCount());
        return "archives";
    }
}
