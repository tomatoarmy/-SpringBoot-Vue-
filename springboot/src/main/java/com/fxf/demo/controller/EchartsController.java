package com.fxf.demo.controller;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fxf.demo.common.Result;
import com.fxf.demo.entity.DayCount;
import com.fxf.demo.entity.Room;
import com.fxf.demo.service.impl.DayCountService;
import com.fxf.demo.service.impl.LiveInfoService;
import com.fxf.demo.service.impl.RoomService;
import com.fxf.demo.service.impl.WeekCountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/echarts")
public class EchartsController {
    @Resource
    RoomService roomService;
    @Resource
    LiveInfoService liveInfoService;
    @Resource
    DayCountService dayCountService;
    @Resource
    WeekCountService weekCountService;

    @GetMapping("/example")
    public Result get(){
        Map<String, Object> map = new HashMap<>();
        map.put("x", CollUtil.newArrayList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
        map.put("y",CollUtil.newArrayList(150, 230, 224, 218, 135, 147, 260));
        return Result.success(map);
    }

    @GetMapping("getCharsData")
    public Result getChartData(){
        return Result.success(roomService.getHomeStatistics());
    }

    @PostMapping("/day")
    public Result getDay(@RequestBody Date date){
             return Result.success(dayCountService.getDayStatistics(date));
    }

    @PostMapping("/week")
    public Result getWeek(@RequestBody Date date){
        return Result.success(weekCountService.getWeekStatistics(date));
    }
}
