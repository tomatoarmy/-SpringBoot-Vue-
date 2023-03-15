package com.fxf.demo.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxf.demo.common.Constants;
import com.fxf.demo.entity.DayCount;
import com.fxf.demo.exception.ServiceException;
import com.fxf.demo.mapper.DayCountMapper;
import com.fxf.demo.service.IDayCountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DayCountService extends ServiceImpl<DayCountMapper, DayCount> implements IDayCountService {

    @Resource
    DayCountMapper dayCountMapper;

    @Override
    public boolean updateDayCountInfo(DayCount dayCount) {
        return dayCountMapper.updateDayCountInfo(dayCount);
    }

    @Override
    public Integer queryMaxId() {
        return dayCountMapper.queryMaxId();
    }

    @Override
    public Map getDayStatistics(Date date) {
        List<DayCount> list = dayCountMapper.getByDate(date);
        Map<String, Object> map = new HashMap<>();
        try{
            DayCount dayCount = list.get(0);
            if (dayCount != null){
                map.put("x", CollUtil.newArrayList("00-05", "06-11", "12-17", "18-23"));
                map.put("y",CollUtil.newArrayList(dayCount.getFirstS(),dayCount.getSecondS(),dayCount.getThirdS(),dayCount.getFourthS()));
                map.put("totalCheckIn",dayCount.getFirstS()+dayCount.getSecondS()+dayCount.getThirdS()+dayCount.getFourthS());
                map.put("income",dayCount.getIncome());
                map.put("orderNum",dayCount.getOrderNum());
            }
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_401,"日期错误");
        }


        return map;
    }
}
