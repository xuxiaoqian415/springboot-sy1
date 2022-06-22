package com.xxq.service.impl;

import com.xxq.dao.EquipDao;
import com.xxq.dao.UserDao;
import com.xxq.dto.EquipDto;
import com.xxq.dto.SearchDto;
import com.xxq.entity.Equipment;
import com.xxq.entity.User;
import com.xxq.service.EquipService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EquipServiceImpl implements EquipService {

    @Autowired
    private EquipDao equipDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Integer addEquip(EquipDto dto) {
        dto.setAddTime(new Date());
        if (dto.getUserId() == null) {
            dto.setUserId(0);
        }
        Equipment equipment = new Equipment();
        BeanUtils.copyProperties(dto, equipment);
        try {
            equipDao.addEquip(equipment);
        } catch (Exception e) {
            return -1;
        }
        return equipment.getId();
    }

    @Override
    public Integer updateEquip(EquipDto dto) {
        if (equipDao.getEquipById(dto.getId()) == null) {
            return -1; // 设备不存在
        }
        Equipment equipment = new Equipment();
        BeanUtils.copyProperties(dto, equipment);
        try {
            equipDao.updateEquipById(equipment);
        } catch (Exception e) {
            return -2;
        }
        return dto.getId();
    }

    @Override
    public Integer deleteEquipById(Integer id) {
        if (equipDao.getEquipById(id) == null) {
            return -1; // 设备不存在
        }
        try {
            equipDao.deleteEquipById(id);
        } catch (Exception e) {
            return -2;
        }
        return id;
    }

    @Override
    public List<EquipDto> searchEquips(SearchDto search) {
        List<Equipment> list = equipDao.searchEquips(search);
        List<EquipDto> dtoList = new ArrayList<>();
        Integer i=0;
        for (Equipment e : list) {
            EquipDto dto = new EquipDto();
            BeanUtils.copyProperties(e, dto);
            User userSearch = new User();
            userSearch.setId(e.getUserId());
            User user = userDao.getUserByValue(userSearch);
            if (user != null) {
                dto.setUserName(user.getRealName());
            }
            else {
                dto.setUserName("未分配用户");
            }
            dtoList.add(i++, dto);
        }
        return dtoList;
    }

    @Override
    public Integer assignEquip(Integer userId, Integer equipId) {
        try {
            equipDao.assignEquip(userId, equipId);
        } catch (Exception e) {
            return -1;
        }
        return equipId;
    }

    @Override
    public Integer unAssignEquip(Integer equipId) {
        try {
            equipDao.unAssignEquip(equipId);
        } catch (Exception e) {
            return -1;
        }
        return equipId;
    }
}
