package cn.mkh.blog.web.controller.admin;

import cn.mkh.blog.web.domain.User;
import cn.mkh.blog.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LogingController {

    @Autowired
    private UserService service;

    @GetMapping
    public String logingPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes){
        User user = service.checkUser(username,password);
        if(user == null){
            redirectAttributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin";
        }
        user.setPassword(null);
        session.setAttribute("user",user);
        return "admin/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("user");
        return "redirect:/admin";
    }
}
