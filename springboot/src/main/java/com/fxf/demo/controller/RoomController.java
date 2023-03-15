package com.fxf.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxf.demo.common.Result;
import com.fxf.demo.entity.Room;
import com.fxf.demo.service.impl.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    RoomService roomService;


    @PostMapping
    public Result save(@RequestBody Room room){
        //调用service层实现新增与更新功能
        return Result.success(roomService.saveOrUpdate(room));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(roomService.removeById(id));
    }

    @PostMapping("/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success(roomService.removeBatchByIds(ids));
    }


    @GetMapping("/pageCheckIn")
    public Result findPageCheckIn(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String roomName){
        QueryWrapper<Room> wrapper = new QueryWrapper<>();
        if (!"".equals(roomName)){
            wrapper.like("roomName",roomName);
        }
        /*让列表倒序展示*/
        wrapper.orderByDesc("roomId");
        wrapper.eq("roomCondition", 0);
        return Result.success(roomService.page(getPage(pageNum, pageSize),wrapper));
    }

    @GetMapping("/pageCheckOut")
    public Result findPageCheckOut(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String roomName){

        QueryWrapper<Room> wrapper = new QueryWrapper<>();
        if (!"".equals(roomName)){
            wrapper.like("roomName",roomName);
        }

        /*让列表倒序展示*/
        wrapper.orderByDesc("roomId");
        wrapper.ne("roomCondition", 0);
        return Result.success(roomService.page(getPage(pageNum, pageSize),wrapper));

    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String roomName){

        QueryWrapper<Room> wrapper = new QueryWrapper<>();
        if (!"".equals(roomName)){
            wrapper.like("roomName",roomName);
        }
        /*让列表倒序展示*/
        wrapper.orderByDesc("roomId");
        return Result.success(roomService.page(getPage(pageNum, pageSize),wrapper));

    }

    @GetMapping("/getPrice")
    public Result getPrice(@RequestParam Integer roomId){
        return Result.success(roomService.getById(roomId).getRoomPrice());
    }

    @GetMapping
    public Result findAll(){
        return Result.success(roomService.list());
    }

    private IPage<Room> getPage(Integer pageNum,Integer pageSize){
        return new Page<>(pageNum, pageSize);
    }

}
