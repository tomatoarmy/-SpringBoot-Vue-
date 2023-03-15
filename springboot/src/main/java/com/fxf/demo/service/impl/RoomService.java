package com.fxf.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxf.demo.entity.Room;
import com.fxf.demo.mapper.RoomMapper;
import com.fxf.demo.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class RoomService extends ServiceImpl<RoomMapper, Room> implements IRoomService {

    @Resource
    RoomMapper roomMapper;


    public Map getHomeStatistics(){
        QueryWrapper<Room> roomWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        //房间总数
        int roomNum = roomMapper.selectList(roomWrapper).size();
        //空闲房间数
        roomWrapper.eq("roomCondition",0);
        int roomLeft = roomMapper.selectList(roomWrapper).size();
        roomWrapper.clear();

        //超时房间数
        roomWrapper.eq("roomCondition",2);
        int roomOverTime = roomMapper.selectList(roomWrapper).size();
        roomWrapper.clear();

        roomWrapper.eq("roomType",0);
        roomWrapper.eq("roomCondition",0);
        int room0 = roomMapper.selectList(roomWrapper).size();
        roomWrapper.clear();

        roomWrapper.eq("roomType",1);
        roomWrapper.eq("roomCondition",0);
        int room1 = roomMapper.selectList(roomWrapper).size();
        roomWrapper.clear();

        roomWrapper.eq("roomType",2);
        roomWrapper.eq("roomCondition",0);
        int room2 = roomMapper.selectList(roomWrapper).size();
        roomWrapper.clear();

        roomWrapper.eq("roomType",3);
        roomWrapper.eq("roomCondition",0);
        int room3 = roomMapper.selectList(roomWrapper).size();
        roomWrapper.clear();

        map.put("roomNum", roomNum);
        map.put("roomLeft",roomLeft);
        map.put("roomOverTime",roomOverTime);
        map.put("room0",room0);
        map.put("room1",room1);
        map.put("room2",room2);
        map.put("room3",room3);

        return map;

    }

   /* @Autowired
    RoomMapper mapper;

    @Override
    public boolean updateRoomInfo(Room room) {
        return mapper.updateRoomInfo(room);
    }

    @Override
    public boolean updateRoomCondition(Room room) {
        return mapper.updateRoomCondition(room);
    }*/
}
