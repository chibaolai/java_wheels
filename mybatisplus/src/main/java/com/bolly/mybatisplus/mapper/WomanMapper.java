package com.bolly.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bolly.mybatisplus.entity.Woman;

/**
 * @author miemie
 * @since 2019-11-27
 */
public interface WomanMapper extends BaseMapper<Woman> {

    Woman selectLinkById(Long id);
}
