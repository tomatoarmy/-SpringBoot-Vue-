package com.fxf.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxf.demo.entity.DayCount;
import com.fxf.demo.entity.WeekCount;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface WeekCountMapper extends BaseMapper<WeekCount> {
    boolean updateWeekCountInfo(WeekCount WeekCount);
    Integer queryMaxId();
    List<WeekCount> getByDate(Date date);
}
