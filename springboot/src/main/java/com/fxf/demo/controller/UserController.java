package com.fxf.demo.controller;



import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxf.demo.common.Constants;
import com.fxf.demo.common.Result;
import com.fxf.demo.controller.dto.UserDTO;
import com.fxf.demo.entity.User;
import com.fxf.demo.service.impl.UserService;
import com.fxf.demo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {



    @Autowired
    UserService userService;

    @PostMapping
    public Result save(@RequestBody User user){
        //调用service层实现新增与更新功能
        return Result.success(userService.saveOrUpdate(user));
    }

    @GetMapping
    public Result findAll(){
        //return userMapper.findAll().toString();
        return Result.success(userService.list());
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        //return userMapper.deleteById(id);
        return Result.success(userService.removeById(id));

    }

    //批量删除的接口
    @PostMapping("/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success(userService.removeBatchByIds(ids));
    }

    //实现分页查询，返回指定页数据和总人数
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String username){
        /*Integer total = userMapper.selectCount(username);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);

        pageNum = (pageNum - 1) * pageSize;
        List<User> users = userMapper.selectPage(pageNum, pageSize, username);
        map.put("data", users);
        return map;*/

        IPage<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (!"".equals(username)){
            wrapper.like("username",username);
            /*如果条件查询中还有其他参数，可以继续加进wrapper中*/
        }
        /*让用户列表倒序展示*/
        wrapper.orderByDesc("id");
        return Result.success(userService.page(page,wrapper));

    }

    /*信息导出接口*/
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception{
        List<User> list = userService.list();
        //在内存操作,写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        /* 设置excel表中的列名,但是导入时不方便
        writer.addHeaderAlias("username","用户名");
        writer.addHeaderAlias("password","密码");
        writer.addHeaderAlias("nickname","昵称");
        writer.addHeaderAlias("email","邮箱");
        writer.addHeaderAlias("phone","电话");
        writer.addHeaderAlias("address","地址");
        writer.addHeaderAlias("create_time","创建时间");
        */

        //一次性写出list类的对象到excel,使用默认样式,强制输出标题
        writer.write(list, true);

        //设置浏览器响应的格式,基本固定
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");

        //获取一个输出流,将writer中胡去的数据传入到输出流中
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }

    @PostMapping("/import")
    public Result imp(MultipartFile file) throws Exception{
        //导入的时候记得把ID列和createTime列删掉,因为那些应该是由数据库自动生成的
        //表中数据的列名应该要和User的属性名对应
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<User> list = reader.readAll(User.class);
        userService.saveBatch(list);
        return Result.success(true);
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400,"参数错误");
        }
        UserDTO dto =  userService.login(userDTO);
        return  Result.success(dto);
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400,"参数错误");
        }
        return Result.success(userService.register(userDTO));
    }

    @GetMapping("/currentUser")
    public Result getCurrentUserInfo(){
        return Result.success(TokenUtils.getCurrentUser());
    }

}
