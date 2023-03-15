package com.fxf.demo.controller.schedule;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fxf.demo.entity.DayCount;
import com.fxf.demo.entity.LiveInfo;
import com.fxf.demo.entity.Order;
import com.fxf.demo.service.impl.DayCountService;
import com.fxf.demo.service.impl.LiveInfoService;
import com.fxf.demo.service.impl.OrderService;
import com.fxf.demo.service.impl.RoomService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class DayCountSchedule {
    @Resource
    RoomService roomService;
    @Resource
    LiveInfoService liveInfoService;
    @Resource
    DayCountService dayCountService;
    @Resource
    OrderService orderService;


    //@Scheduled(cron = "0/15 * * * * ?") //每15秒触发一次,测试用
    @Scheduled(cron = "0 0 0,6,12,18 * * ?")//每天0 6 12 18点执行
    private void updateDayCount() {
        Date now = new Date();
        Date flagDate = DateUtil.offsetHour(now,-1);
        int flagHour = DateUtil.hour(flagDate, true);
        if (dayCountService.list().size() == 0){
            if (flagHour == 23){
                dayCountService.save(new DayCount(1,0,0,0,0,0,DateUtil.yesterday(),0));
            }
            else dayCountService.save(new DayCount(1,0,0,0,0,0,now,0));
        }
        QueryWrapper<LiveInfo> liveWrapper = new QueryWrapper<>();
        QueryWrapper<DayCount> dayWrapper = new QueryWrapper<>();
        liveWrapper.ge("checkInDate", DateUtil.offsetHour(now,-6));
        liveWrapper.le("checkInDate",now);
        int num = liveInfoService.list(liveWrapper).size();

        int income = 0;
        int orderNum = 0;
        liveWrapper.clear();

        //获取最新条目Id

        int dayId = dayCountService.queryMaxId();
        switch (flagHour){
            case 23:{
                QueryWrapper<Order> orderWrapper = new QueryWrapper<>();
                //从前一天的0点开始
                orderWrapper.ge("orderDate", DateUtil.beginOfDay(DateUtil.yesterday()));
                orderWrapper.le("orderDate",now);
                List<Order> orderList = orderService.list(orderWrapper);
                orderNum = orderList.size();
                for (Order order : orderList) {
                    income = income+order.getTotalSpend();
                }
                //mapper中的方法只能一次更新一个属性,所以要分三次更新
                DayCount dayCount = new DayCount(dayId, null, null, null, num, null, null, null);
                dayCountService.updateDayCountInfo(dayCount);
                //更新收入
                dayCount = new DayCount(dayId, null, null, null, null, income, null, null);
                dayCountService.updateDayCountInfo(dayCount);
                //更新订单总数
                dayCount = new DayCount(dayId, null, null, null, null, null, null, orderNum);
                dayCountService.updateDayCountInfo(dayCount);
                //此时前一天的数据已全部统计完成,创建新一天的条目
                dayCountService.save(new DayCount(dayId+1,0,0,0,0,0,now, 0));
                break;
            }
            case 5:{
                DayCount dayCount = new DayCount(dayId, num, null, null, null, null, null,null);
                dayCountService.updateDayCountInfo(dayCount);
                //更新收入,mapper中的方法只能一次更新一个属性,所以要分三次更新
                incomeUpdate(now, dayId);
                orderNumUpdate(now, dayId);
                break;
            }
            case 11:{
                DayCount dayCount = new DayCount(dayId, null, num, null, null, null, null,null);
                dayCountService.updateDayCountInfo(dayCount);
                orderNumUpdate(now, dayId);
                incomeUpdate(now, dayId);
                break;
            }
            case 17:{

                DayCount dayCount = new DayCount(dayId, null, null, num, null, null, null,null);
                dayCountService.updateDayCountInfo(dayCount);
                incomeUpdate(now, dayId);
                orderNumUpdate(now, dayId);
                break;
            }
        }


    }

    private void incomeUpdate(Date now, int dayId) {
        DayCount dayCount;
        int income;
        income = getIncome(now);
        dayCount = new DayCount(dayId, null, null, null, null, income, null,null);
        dayCountService.updateDayCountInfo(dayCount);
    }

    private void orderNumUpdate(Date now, int dayId){
        DayCount dayCount;
        int orderNum;
        orderNum = getOrderNum(now);
        dayCount = new DayCount(dayId, null, null, null, null, null, null,orderNum);
        dayCountService.updateDayCountInfo(dayCount);
    }


    private int getIncome(Date now){
        //计算从今天0点开始到运行时,订单总收入
        QueryWrapper<Order> orderWrapper = new QueryWrapper<>();
        orderWrapper.ge("orderDate", DateUtil.beginOfDay(now));
        orderWrapper.le("orderDate",now);
        List<Order> orderList = orderService.list(orderWrapper);
        int income = 0;
        for (Order order : orderList) {
            income = income+order.getTotalSpend();
        }
        return income;
    }

    private int getOrderNum(Date now){
        QueryWrapper<Order> orderWrapper = new QueryWrapper<>();
        orderWrapper.ge("orderDate", DateUtil.beginOfDay(now));
        orderWrapper.le("orderDate",now);
        List<Order> orderList = orderService.list(orderWrapper);
        return orderList.size();
    }


}
