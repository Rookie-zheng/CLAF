package com.rookie.controller;


import com.rookie.po.Record;
import com.rookie.service.InputService;
import com.rookie.service.OutputService;
import com.rookie.service.RecordService;
import com.rookie.util.ExcelUtils;
import com.rookie.util.RemainingSum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


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
    public String archives(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)Pageable pageable,Model model){
        model.addAttribute("archiveMap",recordService.archiveRecord());
        model.addAttribute("blogCount",recordService.countRecord());
        return "admin/archives";
    }


    @ApiOperation("打印财务记录")
    @RequestMapping("/record/excel")
    public String recordExcel(HttpServletResponse response){
        List<Record> list = recordService.listRecord();
        List<String> head = new ArrayList<>();
        head.add("编号");
        head.add("存入金额");
        head.add("取出金额");
        head.add("存入金钱时间");
        head.add("取出金钱时间");
        head.add("剩余金额");
        head.add("创建时间");
        head.add("备注");

        List<List<String>> body = new ArrayList<>();
        int i = 1;
        for (Record re : list){
            List<String> bodyValue = new ArrayList<>();
            bodyValue.add(String.valueOf(i));
            bodyValue.add(String.valueOf(re.getInputSum()));
            bodyValue.add(String.valueOf(re.getOutputSum()));
            bodyValue.add(String.valueOf(re.getInputTime()));
            bodyValue.add(String.valueOf(re.getOutputTime()));
            bodyValue.add(String.valueOf(re.getSum()));
            bodyValue.add(String.valueOf(re.getCreateTime()));
            bodyValue.add(String.valueOf(re.getRemark()));
            body.add(bodyValue);
            i++;
        }
        String fileName = "财务记录.xls";
        HSSFWorkbook excel = ExcelUtils.expExcel(head,body);
        ExcelUtils.outFile(excel,"./"+fileName,response);
        return "redirect:/admin/archives";
    }
}
