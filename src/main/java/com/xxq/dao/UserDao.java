package com.xxq.dao;

import com.xxq.entity.User;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    Integer addUser(User user);

    User getUserByValue(User user);

    void updateUserById(User user);

    List<User> getAllUsers();

}
