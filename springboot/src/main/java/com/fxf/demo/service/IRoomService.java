package com.fxf.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fxf.demo.entity.Room;

import java.util.Map;


public interface IRoomService extends IService<Room> {

    public Map getHomeStatistics();
    /*boolean updateRoomInfo(Room room);
    boolean updateRoomCondition(Room room);*/
}
