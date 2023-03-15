package com.fxf.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxf.demo.entity.Order;
import com.fxf.demo.entity.Room;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    boolean updateOrderInfo(Order order);
}
