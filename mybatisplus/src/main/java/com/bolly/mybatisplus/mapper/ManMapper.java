package com.bolly.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bolly.mybatisplus.entity.Man;

/**
 * @author miemie
 * @since 2019-11-27
 */
public interface ManMapper extends BaseMapper<Man> {

    Man selectLinkById(Long id);
}
