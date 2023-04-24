package com.exercise.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exercise.zhxy.mapper.GradeMapper;
import com.exercise.zhxy.pojo.Grade;
import com.exercise.zhxy.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service("gradeServiceImpl")
@Transactional
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public IPage<Grade> getGradeByOpr(Page<Grade> page, String gradeName) {
        //设置查询条件
        QueryWrapper<Grade> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(gradeName)){
            queryWrapper.like("name",gradeName);
        }
        //设置排序规则
        queryWrapper.orderByAsc("id");
        //返回分页查询数据
        return gradeMapper.selectPage(page,queryWrapper);
    }

}
