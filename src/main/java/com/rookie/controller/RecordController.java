package com.rookie.controller;


import com.rookie.po.Output;
import com.rookie.po.Record;
import com.rookie.service.InputService;
import com.rookie.service.OutputService;
import com.rookie.service.RecordService;
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
@Api(tags = "后台登录接口")
public class RecordController {

    @Autowired
    private RemainingSum remainingSum;

    @Autowired
    private OutputService outputService;

    @Autowired
    private InputService inputService;

    @Autowired
    private RecordService recordService;

    @ApiOperation("余额接口")
    @GetMapping("/record")
    public String record(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",recordService.listRecord(pageable));
        return "admin/record";
    }

    @ApiOperation("根据id删除分类")
    @GetMapping("/record/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        recordService.deleteRecord(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/record";
    }

    @ApiOperation("文件归档接口")
    @GetMapping("/record/archives")
    public String archives(Model model){
        model.addAttribute("archiveMap",recordService.archiveBlog());
        model.addAttribute("blogCount",recordService.countRecord());
        return "admin/archives";
    }

}
