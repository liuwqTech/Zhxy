package com.exercise.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exercise.zhxy.pojo.Clazz;
import com.exercise.zhxy.service.ClazzService;
import com.exercise.zhxy.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "班级控制器")
@RestController
@RequestMapping("/sms/clazzController")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @ApiOperation("查询班级信息,分页带条件")
    @GetMapping("/getClazzsByOpr/{pageNo}/{pageSize}")
    public Result getClazzsByOpr(
            @ApiParam("分页查询页码数") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("分页查询页大小") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("查询条件") Clazz clazz){
        //设置分页信息
        Page<Clazz> page = new Page<>(pageNo,pageSize);
        //调用服务层方法,传入分页信息和查询的条件
        IPage<Clazz> pageRs = clazzService.getClazzsByOpr(page,clazz);
        //封装Result对象并返回
        return Result.ok(pageRs);
    }

    @ApiOperation("获取所有班级信息")
    @GetMapping("/getClazzs")
    public Result getGrades(){
        List<Clazz> getClazzs = clazzService.getBaseMapper().selectList(null);
        return Result.ok(getClazzs);
    }

    @ApiOperation("添加或者修改班级信息")
    @PostMapping("/saveOrUpdateClazz")
    public Result saveOrUpdateClazz(@ApiParam("JSON的clazz对象") @RequestBody Clazz clazz){
        //调用服务层方法,实现添加或者修改年级信息
        clazzService.saveOrUpdate(clazz);
        return Result.ok();
    }

    @ApiOperation("删除一个或者多个班级信息")
    @DeleteMapping("/deleteClazz")
    public Result deleteClazzByIds(@ApiParam("JSON的班级id集合,映射为后台List<Integer>") @RequestBody List<Integer> ids){
        //调用服务层方法,实现删除年级信息
        clazzService.removeByIds(ids);
        return Result.ok();
    }

}
