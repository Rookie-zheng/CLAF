package com.rookie.controller;


import com.rookie.po.Output;
import com.rookie.service.OutputService;
import com.rookie.util.RemainingSum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
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
public class OutputController {

    @Autowired
    private OutputService outputService;

    @Autowired
    private RemainingSum remainingSum;

    @ApiOperation("取出页显示接口")
    @GetMapping("/output")
    public String output(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)Pageable pageable, Model model){
        model.addAttribute("page",outputService.listOutput(pageable) );
        return "admin/output";
    }

    @ApiOperation("根据id删除取出")
    @GetMapping("/output/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        outputService.deleteOutput(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/output";
    }
    @ApiOperation("取出添加判断是否重复接口")
    @PostMapping("/outputNew")
    public String post(@Valid Output output, BindingResult result, RedirectAttributes attributes, Model model) {

        if (result.hasErrors()) {
            return "admin/outputNew";
        }
        if((outputService.remainAllSum() - output.getPrice()) < 0){
            attributes.addFlashAttribute("message","你的余额不足");
            return "redirect:/admin/output";
        }
        Output t = outputService.saveOutput(output);
        if (t == null ) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/output";
    }

    @ApiOperation("取出修改判断是否重复接口")
    @PostMapping("/output/{id}")
    public String editPost(@Valid Output output, BindingResult result,@PathVariable Long id, RedirectAttributes attributes) throws NotFoundException {
        Output type1 = outputService.getTypeByRemark(output.getRemark());
        if (type1 != null) {
            result.rejectValue("remark","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/outputEdit";
        }
        if((outputService.remainAllSum() - output.getPrice()) < 0){
            attributes.addFlashAttribute("message","你的余额不足");
            return "redirect:/admin/output";
        }
        Output t = outputService.updateOutput(id,output);
        if (t == null ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/output";
    }

    @ApiOperation("根据id修改取出接口")
    @GetMapping("/output/{id}/output")
    public String editOutput(@PathVariable Long id, Model model) {
        Output output = outputService.getOutput(id);
        model.addAttribute("output", outputService.getOutput(id));
        return "admin/outputEdit";
    }

    //新增分类
    @ApiOperation("取出新增接口")
    @GetMapping("/outputNew")
    public String outputNew(Model model) {
        model.addAttribute("output", new Output());
        return "admin/outputNew";
    }
}
