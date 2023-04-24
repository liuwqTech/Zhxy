package com.exercise.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exercise.zhxy.pojo.Grade;
import com.exercise.zhxy.service.GradeService;
import com.exercise.zhxy.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "年级控制器")
@RestController
@RequestMapping("/sms/gradeController")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @ApiOperation("查询年级信息,分页带条件")
    @GetMapping("/getGrades/{pageNo}/{pageSize}")
    public Result getGrades(@ApiParam("分页查询页码数") @PathVariable("pageNo") Integer pageNo, @ApiParam("分页查询页大小") @PathVariable("pageSize") Integer pageSize, @ApiParam("分页查询模糊匹配班级名") String gradeName){
        //设置分页信息
        Page<Grade> page = new Page<>(pageNo,pageSize);
        //调用服务层方法,传入分页信息和查询的条件
        IPage<Grade> pageRs = gradeService.getGradeByOpr(page,gradeName);
        //封装Result对象并返回
        return Result.ok(pageRs);
    }

    @ApiOperation("获取所有年级信息")
    @GetMapping("/getGrades")
    public Result getGrades(){
        List<Grade> gradeList = gradeService.getBaseMapper().selectList(null);
        return Result.ok(gradeList);
    }

    @ApiOperation("添加或者修改年级信息")
    @PostMapping("/saveOrUpdateGrade")
    public Result saveOrUpdateGrade(@ApiParam("JSON的grade对象") @RequestBody Grade grade){
        //调用服务层方法,实现添加或者修改年级信息
        gradeService.saveOrUpdate(grade);
        return Result.ok();
    }

    @ApiOperation("删除一个或者多个年级信息")
    @DeleteMapping("/deleteGrade")
    public Result deleteGradeById(@ApiParam("JSON的年级id集合,映射为后台List<Integer>") @RequestBody List<Integer> ids){
        //调用服务层方法,实现删除年级信息
        gradeService.removeByIds(ids);
        return Result.ok();
    }

}
