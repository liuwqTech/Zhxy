package com.exercise.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exercise.zhxy.mapper.TeacherMapper;
import com.exercise.zhxy.pojo.LoginParam;
import com.exercise.zhxy.pojo.Teacher;
import com.exercise.zhxy.service.TeacherService;
import com.exercise.zhxy.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service("teacherServiceImpl")
@Transactional
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 教师登陆
     * @param loginParam
     * @return
     */
    @Override
    public Teacher login(LoginParam loginParam) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",loginParam.getUsername())
                .eq("password", MD5.encrypt(loginParam.getPassword()));
        Teacher teacher = teacherMapper.selectOne(queryWrapper);
        return teacher;
    }

    /**
     * 根据用户id查询信息
     * @param userId
     * @return
     */
    @Override
    public Teacher getAdminById(Long userId) {
        return teacherMapper.selectById(userId);
    }

    /**
     * 查询教师信息，分页带条件
     * @param page
     * @param teacher
     * @return
     */
    @Override
    public IPage<Teacher> getTeachersByOpr(Page<Teacher> page, Teacher teacher) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(teacher.getClazzName())){
            queryWrapper.eq("clazz_name",teacher.getClazzName());
        }
        if (!StringUtils.isEmpty(teacher.getName())){
            queryWrapper.like("name",teacher.getName());
        }
        queryWrapper.orderByAsc("id");
        return teacherMapper.selectPage(page,queryWrapper);
    }

}
