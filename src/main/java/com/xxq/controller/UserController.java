package com.xxq.controller;

import com.xxq.dto.*;
import com.xxq.service.EquipService;
import com.xxq.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "用户控制器")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private EquipService equipService;

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public String register(@ApiParam("用户注册信息") @RequestBody RegisterDto model, HttpSession session) {
        if (model.getLoginName() == null || model.getPassword() == null || model.getAgainPsw() == null) {
            return "请输入用户名密码，并重复密码";
        }
        if (!model.getPassword().equals(model.getAgainPsw())) {
            return "两次密码不一致，注册失败";
        }
        Integer userId = userService.register(model);
        if (userId == -1) {
            return "用户名已存在";
        }
        if (userId == -2) {
            return "注册失败";
        }
        LoginDto loginDto = new LoginDto();
        loginDto.setLoginName(model.getLoginName());
        loginDto.setPassword(model.getPassword());
        userService.login(loginDto);
        return "注册成功，用户id是：" + userId;
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public String login(@ApiParam("用户登录信息") @RequestBody LoginDto model, HttpSession session) {
        if (model.getLoginName() == null || model.getPassword() == null || model.getType() == null) {
            return "登录信息不完整";
        }
        Integer userId = userService.login(model);
        if (userId == -1) {
            return "密码或类型错误或用户不存在";
        }
        if (userId == -2) {
            return "登录失败";
        }
        session.setAttribute("thisUser", userService.getUserById(userId));
        return "登录成功，用户id是：" + userId;
    }

    @GetMapping("/logout")
    @ApiOperation("用户登出")
    public String logout(HttpSession session) {
        session.invalidate();
        return "用户已登出";
    }

    @GetMapping("/getEquipList")
    @ApiOperation("获取自己名下的设备信息")
    public List<EquipDto> getEquipList(HttpSession session) {
        SearchDto search = new SearchDto();
        Integer userId = ((UserDto) session.getAttribute("thisUser")).getId();
        search.setUserId(userId);
        return equipService.searchEquips(search);
    }

}
