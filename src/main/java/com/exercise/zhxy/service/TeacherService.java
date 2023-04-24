package com.exercise.zhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exercise.zhxy.pojo.LoginParam;
import com.exercise.zhxy.pojo.Teacher;

public interface TeacherService extends IService<Teacher> {
    /**
     * 登陆
     * @param loginParam
     * @return
     */
    Teacher login(LoginParam loginParam);

    /**
     * 根据用户id查询信息
     * @param userId
     * @return
     */
    Teacher getAdminById(Long userId);

    /**
     * 查询教师信息，分页带条件
     * @param page
     * @param teacher
     * @return
     */
    IPage<Teacher> getTeachersByOpr(Page<Teacher> page, Teacher teacher);

}
