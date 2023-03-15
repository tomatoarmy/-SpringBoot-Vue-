package com.fxf.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxf.demo.entity.Room;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoomMapper extends BaseMapper<Room> {
    boolean updateRoomInfo(Room room);
    //boolean updateRoomCondition(Room room);
}
