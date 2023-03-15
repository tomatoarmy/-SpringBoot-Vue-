package com.fxf.demo.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxf.demo.common.Constants;
import com.fxf.demo.entity.DayCount;
import com.fxf.demo.entity.WeekCount;
import com.fxf.demo.exception.ServiceException;
import com.fxf.demo.mapper.WeekCountMapper;
import com.fxf.demo.service.IWeekCountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeekCountService extends ServiceImpl<WeekCountMapper, WeekCount> implements IWeekCountService {
    @Resource
    WeekCountMapper weekCountMapper;


    @Override
    public boolean updateWeekCountInfo(WeekCount weekCount) {
        return weekCountMapper.updateWeekCountInfo(weekCount);
    }

    @Override
    public Integer queryMaxId() {
        return weekCountMapper.queryMaxId();
    }

    @Override
    public Map getWeekStatistics(Date date) {
        List<WeekCount> list = weekCountMapper.getByDate(date);
        Map<String, Object> map = new HashMap<>();
        try{
            WeekCount weekCount = list.get(0);
            if (weekCount != null){
                map.put("x", CollUtil.newArrayList("Monday", "TuesDay", "Wednesday", "Thursday","Friday","Saturday","Sunday"));
                map.put("y",CollUtil.newArrayList(weekCount.getMonday(),weekCount.getTuesday(),
                        weekCount.getWednesday(),weekCount.getThursday(),weekCount.getFriday(),
                        weekCount.getSaturday(),weekCount.getSunday()));
                map.put("totalCheckIn",weekCount.getMonday()+weekCount.getTuesday()+
                        weekCount.getWednesday()+weekCount.getThursday()+weekCount.getFriday()+
                        weekCount.getSaturday()+weekCount.getSunday());
                map.put("income",weekCount.getIncome());
                map.put("orderNum",weekCount.getOrderNum());
            }
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_401,"日期错误");
        }


        return map;
    }
}
