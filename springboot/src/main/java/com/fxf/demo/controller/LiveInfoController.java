package com.fxf.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxf.demo.common.Result;
import com.fxf.demo.entity.LiveInfo;
import com.fxf.demo.entity.Room;
import com.fxf.demo.service.impl.LiveInfoService;
import com.fxf.demo.service.impl.OrderService;
import com.fxf.demo.service.impl.RoomService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/liveInfo")
public class LiveInfoController {



    @Resource
    LiveInfoService liveInfoService;
    @Resource
    RoomService roomService;
    @Resource
    OrderService orderService;


    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String customerName){

        IPage<LiveInfo> page = new Page<>(pageNum, pageSize);
        QueryWrapper<LiveInfo> wrapper = new QueryWrapper<>();
        if (!"".equals(customerName)){
            wrapper.like("customerName",customerName);
        }
        /*让用户列表倒序展示*/
        wrapper.orderByDesc("liveId");
        return Result.success(liveInfoService.page(page,wrapper));
    }

    //生成一条入住信息
    @PostMapping
    public Result save(@RequestBody LiveInfo liveInfo){
        liveInfoService.save(liveInfo);
        int roomId = liveInfo.getRoomId();
        UpdateWrapper<Room> roomUpdateWrapper = new UpdateWrapper<>();
        roomUpdateWrapper.eq("roomId",roomId);
        roomUpdateWrapper.set("roomCondition", 1);//生成入住信息,只可能把roomCondition变为1这一种情况
        roomUpdateWrapper.set("liveId",liveInfo.getLiveId());
        return Result.success(roomService.update(roomUpdateWrapper));
    }


    //根据房间ID返回入住信息
    @GetMapping("/getOne")
    public Result getOne(@RequestParam Integer roomId){
        int liveId = roomService.getById(roomId).getLiveId();
        return Result.success(liveInfoService.getById(liveId));
    }

    @PostMapping("/checkOut")
    public Result checkOut(@RequestBody LiveInfo liveInfo){
        UpdateWrapper<Room> roomUpdateWrapper = new UpdateWrapper<>();
        UpdateWrapper<LiveInfo> liveInfoUpdateWrapper = new UpdateWrapper<>();
        roomUpdateWrapper.eq("roomId",liveInfo.getRoomId());
        roomUpdateWrapper.set("roomCondition", 0);
        roomUpdateWrapper.set("liveId",null);
        liveInfoUpdateWrapper.eq("liveId",liveInfo.getLiveId());
        liveInfoUpdateWrapper.set("liveDays",liveInfo.getLiveDays());
        liveInfoUpdateWrapper.set("checkInDate",liveInfo.getCheckInDate());
        liveInfoUpdateWrapper.set("leaveDate",liveInfo.getLeaveDate());
        return Result.success(roomService.update(roomUpdateWrapper) && liveInfoService.update(liveInfoUpdateWrapper));
    }

    @GetMapping
    public Result findAll(){
        return Result.success(liveInfoService.list());
    }


}
