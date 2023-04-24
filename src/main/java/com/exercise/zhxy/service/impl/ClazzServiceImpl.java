package com.exercise.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exercise.zhxy.mapper.ClazzMapper;
import com.exercise.zhxy.pojo.Clazz;
import com.exercise.zhxy.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service("clazzServiceImpl")
@Transactional
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    /**
     * 查询班级信息，带分页条件
     * @param page
     * @param clazz
     * @return
     */
    @Override
    public IPage<Clazz> getClazzsByOpr(Page<Clazz> page, Clazz clazz) {
        QueryWrapper<Clazz> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(clazz.getGradeName())){
            queryWrapper.like("grade_name",clazz.getGradeName());
        }
        if (!StringUtils.isEmpty(clazz.getName())){
            queryWrapper.like("name",clazz.getName());
        }
        queryWrapper.orderByAsc("id");
        return clazzMapper.selectPage(page,queryWrapper);
    }

}
