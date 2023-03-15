package com.fxf.demo.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxf.demo.common.Constants;
import com.fxf.demo.entity.LiveInfo;
import com.fxf.demo.entity.Order;
import com.fxf.demo.entity.Room;
import com.fxf.demo.exception.ServiceException;
import com.fxf.demo.mapper.OrderMapper;
import com.fxf.demo.mapper.RoomMapper;
import com.fxf.demo.service.IOrderService;
import com.fxf.demo.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Resource
    OrderMapper orderMapper;
    @Resource
    RoomMapper roomMapper;

    @Override
    public boolean updateOrderNote(Order order) {
       return orderMapper.updateOrderInfo(order);
    }

    @Override
    public boolean saveOrder(LiveInfo liveInfo) {
        /*try {
            }catch (Exception e){
            throw new ServiceException(Constants.CODE_500, "订单添加失败");
        }*/
        int liveId = liveInfo.getLiveId();
        int roomId = liveInfo.getRoomId();
        Room room = roomMapper.selectById(roomId);
        int roomType = room.getRoomType();
        Date orderDate = new Date();
        int liveDays = (int)DateUtil.between(liveInfo.getCheckInDate(),orderDate, DateUnit.DAY);
        liveDays++;
        int consume = 0;
        int spend = liveDays*room.getRoomPrice();
        int totalSpend = consume + spend;
        //调用静态方法获取当前用户名
        String eName = TokenUtils.getCurrentUser().getUsername();
        orderMapper.insert(new Order(liveId,roomId,roomType,liveDays,spend,eName,orderDate,consume,totalSpend));

        return true;
    }
}
