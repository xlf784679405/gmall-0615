package com.atguigu.gmall.pms.vo;

import com.atguigu.gmall.pms.entity.ProductAttrValueEntity;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @author feilong
 * @create 2019-11-01 19:43
 */
public class ProductAttrValueVO extends ProductAttrValueEntity {

    public void setValueSelected(List<String> valueSelected){

        this.setAttrValue(StringUtils.join(valueSelected , ","));
    }
}
