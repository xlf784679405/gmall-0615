package com.atguigu.gmall.pms.vo;

import com.atguigu.gmall.pms.entity.SpuInfoEntity;
import lombok.Data;

import java.util.List;

/**
 * @author feilong
 * @create 2019-11-01 19:39
 */
@Data
public class SpuInfoVO extends SpuInfoEntity {

    private List<String> spuImages;

    private List<ProductAttrValueVO> baseAttrs;

    private List<SkuInfoVO> skus;
}
