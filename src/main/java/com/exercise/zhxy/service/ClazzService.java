package com.exercise.zhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exercise.zhxy.pojo.Clazz;

public interface ClazzService extends IService<Clazz> {
    /**
     * 查询班级信息，带分页条件
     * @param page
     * @param clazz
     * @return
     */
    IPage<Clazz> getClazzsByOpr(Page<Clazz> page, Clazz clazz);

}
