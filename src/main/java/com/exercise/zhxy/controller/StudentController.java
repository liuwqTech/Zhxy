package com.exercise.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exercise.zhxy.pojo.Student;
import com.exercise.zhxy.service.StudentService;
import com.exercise.zhxy.util.MD5;
import com.exercise.zhxy.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "学生控制器")
@RestController
@RequestMapping("/sms/studentController")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ApiOperation("查询学生信息,分页带条件")
    @GetMapping("/getStudentByOpr/{pageNo}/{pageSize}")
    public Result getStudentsByOpr(
            @ApiParam("页码数") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("查询条件") Student student){
        Page<Student> page = new Page<>(pageNo,pageSize);
        IPage<Student> studentPage = studentService.getStudentByOpr(page,student);
        return Result.ok(studentPage);
    }

    @ApiOperation("增加学生信息")
    @PostMapping("/addOrUpdateStudent")
    public Result addOrUpdateStudent(@RequestBody Student student){
        //对学生密码进行加密
        if (!StringUtils.isEmpty(student.getPassword())){
            student.setPassword(MD5.encrypt(student.getPassword()));
        }
        //保存学生信息进入数据库
        studentService.saveOrUpdate(student);
        return Result.ok();
    }

    @ApiOperation("删除一个或者多个学生信息")
    @DeleteMapping("/delStudentById")
    public Result deleteStudentById(@ApiParam("多个学生id的JSON") @RequestBody List<Integer> ids){
        studentService.removeByIds(ids);
        return Result.ok();
    }

}
