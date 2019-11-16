package com.atguigu.gmall.pms.vo;

import com.atguigu.gmall.pms.entity.ProductAttrValueEntity;
import lombok.Data;

import java.util.List;

/**
 * @author feilong
 * @create 2019-11-11 20:27
 */
@Data
public class GroupVO {

    private List<ProductAttrValueEntity> baseAttrValues;

    private String groupName;
}
