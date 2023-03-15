package com.fxf.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxf.demo.common.Result;
import com.fxf.demo.entity.LiveInfo;
import com.fxf.demo.entity.Order;
import com.fxf.demo.service.impl.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrderService orderService;

    @PostMapping("/saveOrder")
    public Result save(@RequestBody LiveInfo liveInfo){
        return Result.success(orderService.saveOrder(liveInfo));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String eName){

        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        if (!"".equals(eName)){
            wrapper.like("eName",eName);
        }
        /*让列表倒序展示*/
        wrapper.orderByDesc("orderId");
        return Result.success(orderService.page(getPage(pageNum, pageSize),wrapper));

    }

    @PostMapping("/updateNote")
    public Result updateNote(@RequestBody Order order){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        return Result.success(orderService.updateOrderNote(order));
    }

    private IPage<Order> getPage(Integer pageNum, Integer pageSize){
        return new Page<>(pageNum, pageSize);
    }
}
