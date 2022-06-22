package com.xxq.service.impl;

import com.xxq.dao.UserDao;
import com.xxq.dto.LoginDto;
import com.xxq.dto.RegisterDto;
import com.xxq.dto.UserDto;
import com.xxq.entity.User;
import com.xxq.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(RegisterDto dto) {
        User search = new User();
        search.setLoginName(dto.getLoginName());
        if (userDao.getUserByValue(search) != null) {
            return -1;
        }
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        user.setType(1);
        try {
            userDao.addUser(user);
        } catch (Exception e) {
            return -2;
        }
        return user.getId();
    }

    @Override
    public Integer login(LoginDto dto) {
        User search = new User();
        BeanUtils.copyProperties(dto, search);
        User thisUser = userDao.getUserByValue(search);
        if (thisUser == null) {
            return -1;
        }
        User model = new User();
        model.setId(thisUser.getId());
        model.setLastLoginTime(new Date());
        try {
            userDao.updateUserById(model);
        } catch (Exception e) {
            return -2;
        }
        return thisUser.getId();
    }

    @Override
    public UserDto getUserById(Integer id) {
        User search = new User();
        search.setId(id);
        User thisUser = userDao.getUserByValue(search);
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(thisUser, dto);
        return dto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUsers = userDao.getAllUsers();
        List<UserDto> dtoList = new ArrayList<>();
        Integer i = 0;
        for (User u : allUsers) {
            UserDto dto = new UserDto();
            BeanUtils.copyProperties(u, dto);
            dtoList.add(i++, dto);
        }
        return dtoList;
    }

    @Override
    public Integer resetPsw(RegisterDto dto) {
        User model = new User();
        model.setId(dto.getUserId());
        model.setPassword(dto.getOldPsw());
        User user = userDao.getUserByValue(model);
        if (user == null) {
            return -1; // 原密码错误
        }
        if (!dto.getPassword().equals(dto.getAgainPsw())) {
            return -2; // 两次密码输入不一致
        }
        model.setPassword(dto.getPassword());
        try {
            userDao.updateUserById(model);
        } catch (Exception e) {
            return -3;
        }
        return model.getId();
    }
}
