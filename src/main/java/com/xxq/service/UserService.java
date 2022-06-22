package com.xxq.service;

import com.xxq.dto.LoginDto;
import com.xxq.dto.RegisterDto;
import com.xxq.dto.UserDto;

import java.util.List;

public interface UserService {

    /**
     * @return 新用户id
     */
    Integer register(RegisterDto dto);

    /**
     * @return 用户id
     */
    Integer login(LoginDto dto);

    /**
     * 根据id获取当前用户信息
     */
    UserDto getUserById(Integer id);

    /**
     * 获取用户信息列表
     */
    List<UserDto> getAllUsers();

    /**
     * 修改用户密码
     */
    Integer resetPsw(RegisterDto dto);

}
