package com.fxf.demo.controller.schedule;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fxf.demo.entity.DayCount;
import com.fxf.demo.entity.WeekCount;
import com.fxf.demo.entity.LiveInfo;
import com.fxf.demo.entity.Order;
import com.fxf.demo.service.impl.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class WeekCountSchedule {
    @Resource
    RoomService roomService;
    @Resource
    LiveInfoService liveInfoService;
    @Resource
    WeekCountService weekCountService;
    @Resource
    DayCountService dayCountService;
    @Resource
    OrderService orderService;

    //@Scheduled(cron = "0/15 * * * * ?") //每15秒触发一次,测试用
    @Scheduled(cron = "0 1 0 ? * 1")//每周一0点01分触发,更新上周的数据
    private void updateWeekCount() {
        Date now = new Date();
        if (weekCountService.list().size() == 0){
            weekCountService.save(new WeekCount(1,0,0,0,0,0,0,0,0,DateUtil.lastWeek(),0));
        }

        int weekId = weekCountService.queryMaxId();

        QueryWrapper<DayCount> dayCountWrapper = new QueryWrapper<>();
        dayCountWrapper.ge("createDate",DateUtil.lastWeek());
        dayCountWrapper.le("createDate",now);
        List<DayCount> dayCountList = dayCountService.list(dayCountWrapper);

        int weekIncome = 0;
        int weekOrderNum = 0;
        for (DayCount dayCount : dayCountList) {
            switch (DateUtil.dayOfWeek(dayCount.getCreateDate())){
                //dayOfWeek方法周日为1,周一为2,周六为7以此类推
                case 2:{
                    int num = getTotalCheckIn(dayCount);
                    WeekCount weekCount = new WeekCount(weekId, num, null, null, null, null, null, null, null, null, null);
                    weekCountService.updateWeekCountInfo(weekCount);
                    //累加income
                    weekIncome = weekIncome + dayCount.getIncome();
                    //累加订单总数
                    weekOrderNum = weekOrderNum + dayCount.getOrderNum();
                    break;
                }
                case 3:{
                    int num = getTotalCheckIn(dayCount);
                    WeekCount weekCount = new WeekCount(weekId, null, num, null, null, null, null, null, null, null, null);
                    weekCountService.updateWeekCountInfo(weekCount);
                    weekIncome = weekIncome + dayCount.getIncome();
                    weekOrderNum = weekOrderNum + dayCount.getOrderNum();
                    break;
                }
                case 4:{
                    int num = getTotalCheckIn(dayCount);
                    WeekCount weekCount = new WeekCount(weekId, null, null, num, null, null, null, null, null, null, null);
                    weekCountService.updateWeekCountInfo(weekCount);
                    weekIncome = weekIncome + dayCount.getIncome();
                    weekOrderNum = weekOrderNum + dayCount.getOrderNum();
                    break;
                }
                case 5:{
                    int num = getTotalCheckIn(dayCount);
                    WeekCount weekCount = new WeekCount(weekId, null, null, null, num, null, null, null, null, null, null);
                    weekCountService.updateWeekCountInfo(weekCount);
                    weekIncome = weekIncome + dayCount.getIncome();
                    weekOrderNum = weekOrderNum + dayCount.getOrderNum();
                    break;
                }
                case 6:{
                    int num = getTotalCheckIn(dayCount);
                    WeekCount weekCount = new WeekCount(weekId, null, null, null, null, num, null, null, null, null, null);
                    weekCountService.updateWeekCountInfo(weekCount);
                    weekIncome = weekIncome + dayCount.getIncome();
                    weekOrderNum = weekOrderNum + dayCount.getOrderNum();
                    break;
                }
                case 7:{
                    int num = getTotalCheckIn(dayCount);
                    WeekCount weekCount = new WeekCount(weekId, null, null, null, null, null, num, null, null, null, null);
                    weekCountService.updateWeekCountInfo(weekCount);
                    weekIncome = weekIncome + dayCount.getIncome();
                    weekOrderNum = weekOrderNum + dayCount.getOrderNum();
                    break;
                }
                case 1:{
                    int num = getTotalCheckIn(dayCount);
                    WeekCount weekCount = new WeekCount(weekId, null, null, null, null, null, null, num, null, null, null);
                    weekCountService.updateWeekCountInfo(weekCount);
                    weekIncome = weekIncome + dayCount.getIncome();
                    weekOrderNum = weekOrderNum + dayCount.getOrderNum();
                    break;
                }
            }
            }
        //更新订房和总收入
        WeekCount weekCount = new WeekCount(weekId, null, null, null, null, null, null, null, weekIncome, null, null);
        weekCountService.updateWeekCountInfo(weekCount);
        weekCount = new WeekCount(weekId, null, null, null, null, null, null, null, null, null, weekOrderNum);
        weekCountService.updateWeekCountInfo(weekCount);
        //创建本周表目
            /*System.out.println(weekId);
             weekId = weekCountService.queryMaxId();
            System.out.println(weekId);*/
        weekCountService.save(new WeekCount(weekId+1,0,0,0,0,0,0,0,0,now,0));


    }


    private int getTotalCheckIn(DayCount dayCount){
        int sum = dayCount.getFirstS()+dayCount.getSecondS()+dayCount.getThirdS()+dayCount.getFourthS();
        return sum;
    }

}
