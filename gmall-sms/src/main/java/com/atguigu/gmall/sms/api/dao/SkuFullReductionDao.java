package com.atguigu.gmall.sms.api.dao;

import com.atguigu.gmall.sms.api.entity.SkuFullReductionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品满减信息
 * 
 * @author xinlongfei
 * @email xlf@atguigu.com
 * @date 2019-10-29 16:40:42
 */
@Mapper
public interface SkuFullReductionDao extends BaseMapper<SkuFullReductionEntity> {
	
}
