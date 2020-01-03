package com.rookie.controller;

import com.rookie.po.Input;
import com.rookie.service.InputService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
@Api(tags = "存款接口")
public class InputController {

    @Autowired
    private InputService inputService;

    @ApiOperation("存入页显示接口")
    @GetMapping("/input")
    public String input(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)Pageable pageable, Model model){
        model.addAttribute("page",inputService.listInput(pageable) );
        return "admin/input";
    }

    @ApiOperation("根据id删除存入")
    @GetMapping("/input/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        inputService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/input";
    }
    @ApiOperation("存入接口")
    @PostMapping("/inputNew")
    public String post(@Valid Input input, BindingResult result, RedirectAttributes attributes, Model model) {
//        Input type1 = inputService.getTypeByRemark(input.getRemark());
//        if (type1 != null) {
//            result.rejectValue("remark","nameError","不能添加重复的分类");
//        }
        if (result.hasErrors()) {
            return "admin/inputNew";
        }
        Input t = inputService.saveInput(input);
        if (t == null ) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/input";
    }

    @ApiOperation("存入修改判断是否重复接口")
    @PostMapping("/input/{id}")
    public String editPost(@Valid Input input, BindingResult result,@PathVariable Long id, RedirectAttributes attributes) {
//        Input type1 = inputService.getTypeByRemark(input.getRemark());
//        if (type1 != null) {
//            result.rejectValue("remark","nameError","不能添加重复的分类");
//        }
        if (result.hasErrors()) {
            return "admin/inputEdit";
        }
        Input t = inputService.updateType(id,input);
        if (t == null ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/input";
    }

    @ApiOperation("根据id修改存入接口")
    @GetMapping("/input/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        Input input = inputService.getInput(id);
        model.addAttribute("input", inputService.getInput(id));
        return "admin/inputEdit";
    }

    //新增分类
    @ApiOperation("存入新增接口")
    @GetMapping("/inputNew")
    public String inputNew(Model model) {
        model.addAttribute("input", new Input());
        return "admin/inputNew";
    }
}
