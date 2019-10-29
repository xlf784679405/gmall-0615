package com.atguigu.gmall.pms.dao;

import com.atguigu.gmall.pms.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author xinlongfei
 * @email lxf@atguigu.com
 * @date 2019-10-29 16:28:06
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
