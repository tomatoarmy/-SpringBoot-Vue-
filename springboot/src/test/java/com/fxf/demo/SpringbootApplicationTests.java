package com.fxf.demo;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fxf.demo.entity.DayCount;
import com.fxf.demo.entity.User;
import com.fxf.demo.entity.WeekCount;
import com.fxf.demo.mapper.DayCountMapper;
import com.fxf.demo.mapper.WeekCountMapper;
import com.fxf.demo.service.impl.DayCountService;
import com.fxf.demo.service.impl.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class SpringbootApplicationTests {

    @Autowired
    UserService service;
    @Autowired
    DayCountService dayCountService;
    @Autowired
    DayCountMapper mapper;
    @Autowired
    DayCountMapper dayCountMapper;
    @Autowired
    WeekCountMapper weekCountMapper;

    @Test
    void contextLoads() {
        /*Date date = new Date();
        int i = DateUtil.dayOfWeek(date);
        int i1 = DateUtil.dayOfWeek(DateUtil.yesterday());
        int i2 = DateUtil.dayOfWeek(DateUtil.tomorrow());
        System.out.println("============----=========");
        System.out.println("============----=========");
        System.out.println(date);
        System.out.println(DateUtil.yesterday());
        System.out.println(i2);
        System.out.println("============----=========>");
        System.out.println("============----=========>");*/
        DayCount dayCount = new DayCount(3, null, null, null, null, null, null,2);
        dayCountService.updateDayCountInfo(dayCount);

    }


}
