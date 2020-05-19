package com.bolly.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bolly.mybatisplus.entity.Child;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author miemie
 * @since 2019-11-27
 */
public interface ChildMapper extends BaseMapper<Child> {

    Child selectLinkById(Long id);

    @Select("select * from child where lao_han_id = #{id}")
    List<Child> selectByLaoHanId(Long id);

    @Select("select * from child where lao_ma_id = #{id}")
    List<Child> selectByLaoMaId(Long id);
}
