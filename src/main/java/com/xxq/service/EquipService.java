package com.xxq.service;

import com.xxq.dto.EquipDto;
import com.xxq.dto.SearchDto;

import java.util.List;

public interface EquipService {

    Integer addEquip(EquipDto dto);

    Integer updateEquip(EquipDto dto);

    Integer deleteEquipById(Integer id);

    List<EquipDto> searchEquips(SearchDto search);

    Integer assignEquip(Integer userId, Integer equipId);

    Integer unAssignEquip(Integer equipId);
}
