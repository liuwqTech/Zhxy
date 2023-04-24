package com.exercise.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exercise.zhxy.pojo.Admin;
import com.exercise.zhxy.service.AdminService;
import com.exercise.zhxy.util.MD5;
import com.exercise.zhxy.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "系统管理员控制器")
@RestController
@RequestMapping("/sms/adminController")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ApiOperation("分页获取所有的管理员信息【带条件】")
    @GetMapping("/getAllAdmin/{pageNo}/{pageSize}")
    public Result getAllAdmin(
            @ApiParam("页码数") @PathVariable Integer pageNo,
            @ApiParam("页大小") @PathVariable Integer pageSize,
            @ApiParam("查询条件") String adminName){
        Page<Admin> pageParam = new Page<>(pageNo,pageSize);
        IPage<Admin> page = adminService.getAdmins(pageParam, adminName);
        return Result.ok(page);
    }

    @ApiOperation("添加或修改管理员信息")
    @PostMapping("/saveOrUpdateAdmin")
    public Result saveOrUpdateAdmin(@ApiParam("要新增或修改的JSON格式的Admin") @RequestBody Admin admin){
        if (!StringUtils.isEmpty(admin.getPassword())) {
            admin.setPassword(MD5.encrypt(admin.getPassword()));
        }
        adminService.saveOrUpdate(admin);
        return Result.ok();
    }

    @ApiOperation("删除一个或多个管理员信息")
    @DeleteMapping("/deleteAdmin")
    public Result deleteAdminByIds(@ApiParam("多个管理员id的JSON") @RequestBody List<Integer> ids){
        adminService.removeByIds(ids);
        return Result.ok();
    }

}
