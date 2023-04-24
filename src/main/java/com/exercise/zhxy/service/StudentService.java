package com.exercise.zhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exercise.zhxy.pojo.LoginParam;
import com.exercise.zhxy.pojo.Student;

public interface StudentService extends IService<Student> {
    /**
     * 登陆
     * @param loginParam
     * @return
     */
    Student login(LoginParam loginParam);

    /**
     * 根据用户id查询信息
     * @param userId
     * @return
     */
    Student getAdminById(Long userId);

    /**
     * 查询学生信息，带分页条件
     * @param page
     * @param student
     * @return
     */
    IPage<Student> getStudentByOpr(Page<Student> page, Student student);

}
