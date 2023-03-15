package com.fxf.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxf.demo.entity.LiveInfo;
import com.fxf.demo.mapper.LiveInfoMapper;
import com.fxf.demo.service.ILiveInfoService;
import org.springframework.stereotype.Service;

@Service
public class LiveInfoService extends ServiceImpl<LiveInfoMapper, LiveInfo> implements ILiveInfoService {
}
