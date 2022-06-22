package com.xxq.controller;

import com.xxq.dto.EquipDto;
import com.xxq.dto.RegisterDto;
import com.xxq.dto.SearchDto;
import com.xxq.dto.UserDto;
import com.xxq.service.EquipService;
import com.xxq.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Api(tags = "管理员控制器")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private EquipService equipService;

    @GetMapping("/getUserList")
    @ApiOperation("管理员查看用户信息")
    public List<UserDto> getUserList() {
        List<UserDto> userList = userService.getAllUsers();
        return userList;
    }

    @PostMapping("/addUser")
    @ApiOperation("管理员添加普通用户")
    public String addUser(@ApiParam("用户信息") @RequestBody RegisterDto model) {
        if (model.getLoginName() == null) {
            return "请输入用户名";
        }
        model.setPassword("123456"); // 默认密码
        Integer userId = userService.register(model);
        if (userId == -1) {
            return "用户名已存在";
        }
        if (userId == -2) {
            return "添加失败";
        }
        return "注册成功，用户id是：" + userId;
    }

    @PostMapping("/resetPsw")
    @ApiOperation("管理员重置用户密码")
    public String resetPsw(@ApiParam("用户信息") @RequestBody RegisterDto model) {
        if (model.getUserId() == null) {
            return "缺失用户id";
        }
        if (model.getOldPsw() == null || model.getPassword() == null || model.getAgainPsw() == null) {
            return "请输入原密码、新密码并重复新密码";
        }
        Integer code = userService.resetPsw(model);
        if (code == -1) {
            return "原密码错误";
        }
        if (code == -2) {
            return "两次密码输入不一致";
        }
        if (code == -3) {
            return "修改密码失败";
        }
        return "密码修改成功";
    }

    @PostMapping("/addEquip")
    @ApiOperation("管理员添加设备")
    public String addEquip(@ApiParam("设备信息") @RequestBody EquipDto model) {
        if (model.getName() == null || model.getCode() == null) {
            return "设备信息不全，添加失败";
        }
        if (model.getUserId() != null && model.getUserId() != 0) {
            if (userService.getUserById(model.getUserId()) == null) {
                return "设备所属用户不存在";
            }
        }
        Integer equipId = equipService.addEquip(model);
        if (equipId == -1) {
            return "添加失败";
        }
        return "添加成功，设备ID是" + equipId;
    }

    @PostMapping("/updateEquip")
    @ApiOperation("管理员修改设备")
    public String updateEquip(@ApiParam("设备信息") @RequestBody EquipDto model) {
        if (model.getId() == null) {
            return "缺失设备id";
        }
        Integer code = equipService.updateEquip(model);
        if (code == -1) {
            return "设备不存在";
        }
        if (code == -2) {
            return "设备修改失败";
        }
        return "修改成功";
    }

    @GetMapping("/deleteEquip/{id}")
    @ApiOperation("删除设备")
    public String deleteEquip(@PathVariable("id") Integer id) {
        Integer code = equipService.deleteEquipById(id);
        if (code == -1) {
            return "设备不存在";
        }
        if (code == -2) {
            return "删除失败";
        }
        return "删除成功";
    }

    @PostMapping("/getEquipList")
    @ApiOperation("获取设备信息")
    public List<EquipDto> getEquipList(@ApiParam("搜索信息") @RequestBody SearchDto search) {
        return equipService.searchEquips(search);
    }

    @GetMapping("/assignEquip")
    @ApiOperation("分配设备")
    public String assignEquip(@RequestParam("userId") Integer userId, @RequestParam("equipId")Integer equipId) {
        if (equipService.assignEquip(userId, equipId) == -1) {
            return "分配失败";
        }
        return "设备" + equipId + "分配成功";
    }

    @GetMapping("/unAssignEquip")
    @ApiOperation("设备回收")
    public String unAssignEquip(@RequestParam("equipId")Integer equipId) {
        if (equipService.unAssignEquip(equipId) == -1) {
            return "回收失败";
        }
        return "设备" + equipId + "回收成功";
    }
}
