package cn.mkh.blog.web.controller.admin;

import cn.mkh.blog.web.domain.Type;
import cn.mkh.blog.web.service.TypesService;
import com.github.pagehelper.PageInfo;
import jdk.internal.dynalink.support.TypeUtilities;
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
public class TypesAdminController {

    @Autowired
    private TypesService service;

    //展示tayp列表方法
    @GetMapping("/types")
    public String findAllTypes(Model model,
                               @RequestParam(defaultValue = "1",required = false) Integer pageNum,
                               @RequestParam(defaultValue = "5",required = false) Integer pageSize){
        List<Type> types =  service.findAllTypes(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(types);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    //前往添加页面方法
    @GetMapping("/type")
    public String toAddPage(Model model){
        model.addAttribute("type",new Type());
        return "admin/type-add";
    }

    //添加Type类
    @PostMapping("/type")
    //@Valid表明是要对Type类进行校验，BindingResult会产生校验结果
    public String addType(@Valid Type type, BindingResult result, RedirectAttributes redirectAttributes){
        //校验是否已存在相同的Type
        Type type1 = service.findByName(type);
        if(type1 != null){
            result.rejectValue("name","nameError","不能添加重复的类");
        }
        //校验是否数据错误
        if(result.hasErrors()){
            return "admin/type-add";
        }
        Integer id = service.addType(type);
        //校验是否存储成功
        if(id != null){
            redirectAttributes.addFlashAttribute("message","添加成功");
        }else {
            redirectAttributes.addFlashAttribute("message","添加失败");
        }
        return "redirect:/admin/types";
    }

    //删除方法
    @GetMapping("/type/{id}/delete")
    public String delType(@PathVariable Integer id,RedirectAttributes redirectAttributes){
        service.delType(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }

    //去往编辑页面
    @GetMapping("/type/{id}")
    public String toEditPage(@PathVariable Integer id,Model model){
        Type type = service.findById(id);
        model.addAttribute("type", type);
        return "admin/type-add";
    }
    //修改方法
    @PostMapping("/type/{id}")
    //@Valid表明是要对Type类进行校验，BindingResult会产生校验结果
    public String updateType(@Valid Type type, BindingResult result,RedirectAttributes redirectAttributes){
        //校验是否已存在相同的Type
        Type type1 = service.findByName(type);
        if(type1 != null){
            result.rejectValue("name","nameError","不能添加重复的类");
        }
        //校验是否数据错误
        if(result.hasErrors()){
            return "admin/type-add";
        }
        Integer id1 = service.updateType(type);
        //校验是否存储成功
        if(id1 != null){
            redirectAttributes.addFlashAttribute("message","修改成功");
        }else {
            redirectAttributes.addFlashAttribute("message","修改失败");
        }
        return "redirect:/admin/types";
    }
}
