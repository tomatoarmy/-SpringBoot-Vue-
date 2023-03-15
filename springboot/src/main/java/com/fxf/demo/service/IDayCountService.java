package com.fxf.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fxf.demo.entity.DayCount;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IDayCountService extends IService<DayCount> {
    boolean updateDayCountInfo(DayCount dayCount);
    Integer queryMaxId();
    Map getDayStatistics(Date date);
}
