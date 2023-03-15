package com.fxf.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fxf.demo.entity.LiveInfo;
import com.fxf.demo.entity.Order;
import com.fxf.demo.entity.Room;

public interface IOrderService extends IService<Order> {
    boolean updateOrderNote(Order order);

    boolean saveOrder(LiveInfo liveInfo);


}
