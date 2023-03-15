package com.fxf.demo.controller.schedule;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fxf.demo.entity.DayCount;
import com.fxf.demo.entity.LiveInfo;
import com.fxf.demo.entity.Order;
import com.fxf.demo.entity.Room;
import com.fxf.demo.service.impl.DayCountService;
import com.fxf.demo.service.impl.LiveInfoService;
import com.fxf.demo.service.impl.OrderService;
import com.fxf.demo.service.impl.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class RoomDepositSchedule {
    //每天上午10点15触发

    @Resource
    RoomService roomService;
    @Resource
    LiveInfoService liveInfoService;
    @Resource
    DayCountService dayCountService;
    @Resource
    OrderService orderService;


    @Scheduled(cron = "0 0 12 * * ?")//每天12点执行
    //@Scheduled(cron = "0/30 * * * * ?") //每10秒触发一次,测试用
    private void updateDepositLeft() {
        List<Room> roomList = roomService.list();
        for (Room room : roomList) {
            if (room.getRoomCondition() == 1) {//如果房间处于入住状态
                if (liveInfoService.getById(room.getLiveId()).getDepositLeft() <= room.getRoomPrice()) {//如果房间的剩余押金只够支付两天的房钱(要多交一天的房钱作为押金),将房间状态改为今日到期
                    UpdateWrapper<Room> roomUpdateWrapper = new UpdateWrapper<>();
                    roomUpdateWrapper.eq("roomId", room.getRoomId());
                    roomUpdateWrapper.set("roomCondition", 2);
                    roomService.update(roomUpdateWrapper);
                    System.out.println("房间" + room.getRoomName() + "已超时");
                }
                UpdateWrapper<LiveInfo> liveInfoWrapper = new UpdateWrapper<>();
                liveInfoWrapper.eq("liveId", room.getLiveId());
                liveInfoWrapper.set("depositLeft", liveInfoService.getById(room.getLiveId()).getDepositLeft() - room.getRoomPrice());
                liveInfoService.update(liveInfoWrapper);
            }
        }
    }


}
