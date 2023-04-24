package com.exercise.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exercise.zhxy.mapper.StudentMapper;
import com.exercise.zhxy.pojo.LoginParam;
import com.exercise.zhxy.pojo.Student;
import com.exercise.zhxy.service.StudentService;
import com.exercise.zhxy.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service("studentServiceImpl")
@Transactional
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 学生登陆
     * @param loginParam
     * @return
     */
    @Override
    public Student login(LoginParam loginParam) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",loginParam.getUsername())
                .eq("password", MD5.encrypt(loginParam.getPassword()));
        Student student = studentMapper.selectOne(queryWrapper);
        return student;
    }

    /**
     * 根据用户id查询信息
     * @param userId
     * @return
     */
    @Override
    public Student getAdminById(Long userId) {
        return studentMapper.selectById(userId);
    }

    /**
     * 查询学生信息，带分页条件
     * @param page
     * @param student
     * @return
     */
    @Override
    public IPage<Student> getStudentByOpr(Page<Student> page, Student student) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(student.getClazzName())){
            queryWrapper.eq("clazz_name",student.getClazzName());
        }
        if (!StringUtils.isEmpty(student.getName())){
            queryWrapper.eq("name",student.getName());
        }
        queryWrapper.orderByAsc("id");
        return studentMapper.selectPage(page,queryWrapper);
    }

}
