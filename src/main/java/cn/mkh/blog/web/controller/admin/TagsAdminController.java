package cn.mkh.blog.web.controller.admin;

import cn.mkh.blog.web.domain.Tag;
import cn.mkh.blog.web.service.TagsService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagsAdminController {

    @Autowired
    private TagsService service;

    //展示tag列表方法
    @GetMapping("/tags")
    public String findAllTags(Model model,
                              @RequestParam(defaultValue = "1",required = false) Integer pageNum,
                              @RequestParam(defaultValue = "5",required = false) Integer pageSize){
        List<Tag> tags =  service.findAllTags(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(tags);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }

    //前往添加页面方法
    @GetMapping("/tag")
    public String toAddPage(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tag-add";
    }

    //添加Tag类
    @PostMapping("/tag")
    //@Valid表明是要对Tag类进行校验，BindingResult会产生校验结果
    public String addTag(@Valid Tag tag, BindingResult result, RedirectAttributes redirectAttributes){
        //校验是否已存在相同的Tag
        Tag tag1 = service.findByName(tag);
        if(tag1 != null){
            result.rejectValue("name","nameError","不能添加重复的类");
        }
        //校验是否数据错误
        if(result.hasErrors()){
            return "admin/tag-add";
        }
        Integer id = service.addTag(tag);
        //校验是否存储成功
        if(id != null){
            redirectAttributes.addFlashAttribute("message","添加成功");
        }else {
            redirectAttributes.addFlashAttribute("message","添加失败");
        }
        return "redirect:/admin/tags";
    }

    //删除方法
    @GetMapping("/tag/{id}/delete")
    public String delTag(@PathVariable Integer id,RedirectAttributes redirectAttributes){
        service.delTag(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }

    //去往编辑页面
    @GetMapping("/tag/{id}")
    public String toEditPage(@PathVariable Integer id,Model model){
        Tag tag = service.findById(id);
        model.addAttribute("tag", tag);
        return "admin/tag-add";
    }
    //修改方法
    @PostMapping("/tag/{id}")
    //@Valid表明是要对Tag类进行校验，BindingResult会产生校验结果
    public String updateTag(@Valid Tag tag, BindingResult result,RedirectAttributes redirectAttributes){
        //校验是否已存在相同的Tag
        Tag tag1 = service.findByName(tag);
        if(tag1 != null){
            result.rejectValue("name","nameError","不能添加重复的类");
        }
        //校验是否数据错误
        if(result.hasErrors()){
            return "admin/tag-add";
        }
        Integer id1 = service.updateTag(tag);
        //校验是否存储成功
        if(id1 != null){
            redirectAttributes.addFlashAttribute("message","修改成功");
        }else {
            redirectAttributes.addFlashAttribute("message","修改失败");
        }
        return "redirect:/admin/tags";
    }
}
