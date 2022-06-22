package com.xxq.dao;

import com.xxq.dto.SearchDto;
import com.xxq.entity.Equipment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EquipDao {

    Integer addEquip(Equipment equipment);

    Equipment getEquipById(@Param("id") Integer id);

    void updateEquipById(Equipment equipment);

    void deleteEquipById(@Param("id") Integer id);

    List<Equipment> searchEquips(SearchDto search);

    void assignEquip(@Param("userId")Integer userId, @Param("equipId")Integer equipId);

    void unAssignEquip(@Param("equipId")Integer equipId);
}
