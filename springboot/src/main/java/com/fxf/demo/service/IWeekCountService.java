package com.fxf.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fxf.demo.entity.DayCount;
import com.fxf.demo.entity.WeekCount;

import java.util.Date;
import java.util.Map;

public interface IWeekCountService extends IService<WeekCount> {
    boolean updateWeekCountInfo(WeekCount weekCount);
    Integer queryMaxId();
    Map getWeekStatistics(Date date);
}
