package com.exercise.zhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exercise.zhxy.pojo.Admin;
import com.exercise.zhxy.pojo.LoginParam;

public interface AdminService extends IService<Admin> {
    /**
     * 登陆
     * @param loginParam
     * @return
     */
    Admin login(LoginParam loginParam);

    /**
     * 根据用户id查询信息
     * @param userId
     * @return
     */
    Admin getAdminById(Long userId);

    /**
     * 查询管理员信息，分页带条件
     * @param pageParam
     * @param adminName
     * @return
     */
    IPage<Admin> getAdmins(Page<Admin> pageParam, String adminName);

}
