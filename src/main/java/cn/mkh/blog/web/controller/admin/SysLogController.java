package cn.mkh.blog.web.controller.admin;

import cn.mkh.blog.web.domain.SysLog;
import cn.mkh.blog.web.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {


    @Autowired
    SysLogService sysLogService;


    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        List<SysLog> list  = sysLogService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sysLogs",list);
        modelAndView.setViewName("asd");
        return modelAndView;
    }
}
