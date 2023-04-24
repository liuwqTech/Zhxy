package com.exercise.zhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exercise.zhxy.pojo.Grade;

public interface GradeService extends IService<Grade> {
    /**
     * 根据年级名称分页查询
     * @param page
     * @param gradeName
     * @return
     */
    IPage<Grade> getGradeByOpr(Page<Grade> page, String gradeName);

}
