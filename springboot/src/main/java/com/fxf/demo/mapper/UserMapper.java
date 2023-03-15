package com.fxf.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxf.demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface UserMapper extends BaseMapper<User> {


    List<User> findAll();


    //mybatis-puls中写好了这个方法,如果对mybatis-plus中提供的这个方法不满意可以
    //自己重写这个查询,在User.xml中配置相应的查询语句,自己写的查询语句将会覆盖
    //mybatis-plus提供的功能
    //int insert(User user);


    //int update(User user);

    //@Delete("delete from 1bishe.sys_user where id = #{id}")
    //Integer deleteById(Integer id);

    /*这里把方法注释掉是因为mybatis-plus中已经有这个方法了，在方法名相同的情况下，会优先使用我们提供的方法*/
    /*而这里我们用mybatis-plus实现分页查询的时候，我们就要把这个方法注释掉*/
    //@Select("select  * from 1bishe.sys_user where username like #{userName} limit #{pageNum}, #{pageSize}")
    //List<User> selectPage(Integer pageNum, Integer pageSize, String userName);

    //@Select("select count(*) from 1bishe.sys_user where username like #{userName}")
   // Integer selectCount(String userName);


}
