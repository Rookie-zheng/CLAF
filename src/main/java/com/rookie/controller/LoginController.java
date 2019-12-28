package com.rookie.controller;

import com.rookie.dao.UserRepository;
import com.rookie.po.Record;
import com.rookie.po.User;
import com.rookie.service.RecordService;
import com.rookie.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
@Api(tags = "后台登录接口")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private RecordService recordService;
    @Autowired
    public UserRepository userRepository;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }
    @ApiOperation("用户登录接口")
    @RequestMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username,password);
        if(user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else {
            attributes.addFlashAttribute("用户名或密码错误");
            return "redirect:/admin";
        }
    }

    @ApiOperation("用户注销接口")
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
