package com.exercise.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exercise.zhxy.pojo.Teacher;
import com.exercise.zhxy.service.TeacherService;
import com.exercise.zhxy.util.MD5;
import com.exercise.zhxy.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "教师控制器")
@RestController
@RequestMapping("/sms/teacherController")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("获取教师信息,分页带条件")
    @GetMapping("/getTeachers/{pageNo}/{pageSize}")
    public Result getTeachers(
            @ApiParam("页码数") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("查询条件") Teacher teacher){
        Page<Teacher> page = new Page<>(pageNo,pageSize);
        IPage<Teacher> teacherPage = teacherService.getTeachersByOpr(page,teacher);
        return Result.ok(teacherPage);
    }

    @ApiOperation("添加和修改教师信息")
    @PostMapping("/saveOrUpdateTeacher")
    public Result saveOrUpdateTeacher(@ApiParam("要保存或者修改的JSON格式的Teacher") @RequestBody Teacher teacher){
        if (!StringUtils.isEmpty(teacher.getPassword())){
            teacher.setPassword(MD5.encrypt(teacher.getPassword()));
        }
        teacherService.saveOrUpdate(teacher);
        return Result.ok();
    }

    @ApiOperation("删除一个或多个教师信息")
    @DeleteMapping("/deleteTeacher")
    public Result deleteTeacherByIds(@ApiParam("多个教师id的JSON") @RequestBody List<Integer> ids){
        teacherService.removeByIds(ids);
        return Result.ok();
    }
}
