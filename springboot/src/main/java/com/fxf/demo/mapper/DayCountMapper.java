package com.fxf.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxf.demo.entity.DayCount;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;


@Mapper
public interface DayCountMapper extends BaseMapper<DayCount> {
    boolean updateDayCountInfo(DayCount dayCount);
    Integer queryMaxId();
    List<DayCount> getByDate(Date date);
}
