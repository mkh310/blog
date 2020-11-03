package cn.mkh.blog.web.controller;

import cn.mkh.blog.web.domain.Comment;
import cn.mkh.blog.web.domain.User;
import cn.mkh.blog.web.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentsController {
    @Autowired
    CommentsService commentsService;

    @GetMapping("/comments/{blogId}")
    public String  loadComments(@PathVariable("blogId") Integer blogId, Model model){
        List<Comment> commentList = commentsService.findCommentsByBlogId(blogId);
        model.addAttribute("commentList",commentList);
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String saveComment(Comment comment, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user != null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else {
            comment.setAvatar("https://s1.ax1x.com/2020/11/03/Bs4o01.jpg");
        }
        commentsService.addComment(comment);
        return "redirect:/comments/"+comment.getBlogId();
    }
}
