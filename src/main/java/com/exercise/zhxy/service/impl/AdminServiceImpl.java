package com.exercise.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exercise.zhxy.mapper.AdminMapper;
import com.exercise.zhxy.pojo.Admin;
import com.exercise.zhxy.pojo.LoginParam;
import com.exercise.zhxy.service.AdminService;
import com.exercise.zhxy.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service("adminServiceImpl")
@Transactional
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 管理员登陆
     * @param loginParam
     * @return
     */
    @Override
    public Admin login(LoginParam loginParam) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",loginParam.getUsername())
                        .eq("password", MD5.encrypt(loginParam.getPassword()));
        Admin admin = adminMapper.selectOne(queryWrapper);
        return admin;
    }

    /**
     * 根据用户id查询信息
     * @param userId
     * @return
     */
    @Override
    public Admin getAdminById(Long userId) {
        return adminMapper.selectById(userId);
    }

    /**
     * 查询管理员信息，分页带条件
     * @param pageParam
     * @param adminName
     * @return
     */
    @Override
    public IPage<Admin> getAdmins(Page<Admin> pageParam, String adminName) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(adminName)){
            queryWrapper.like("name",adminName);
        }
        queryWrapper.orderByAsc("id");
        return adminMapper.selectPage(pageParam,queryWrapper);
    }

}
