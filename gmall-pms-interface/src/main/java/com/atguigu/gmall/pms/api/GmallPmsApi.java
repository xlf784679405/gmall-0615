

package com.atguigu.gmall.pms.api;

import com.atguigu.core.bean.QueryCondition;
import com.atguigu.core.bean.Resp;
import com.atguigu.gmall.pms.entity.*;
import com.atguigu.gmall.pms.vo.CategoryVO;
import com.atguigu.gmall.pms.vo.GroupVO;
import com.atguigu.gmall.pms.vo.SpuAttributeValueVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface  GmallPmsApi {

    @GetMapping("pms/skusaleattrvalue/sku/{skuId}")
    public Resp<List<SkuSaleAttrValueEntity>> querySaleAttrBySkuId(@PathVariable("skuId")Long skuId);

    //分页查询spu
    @PostMapping("pms/spuinfo/list")
    public Resp<List<SpuInfoEntity>> querySpuPage(@RequestBody QueryCondition queryCondition);

    //根据spuId查询sku
    @GetMapping("pms/skuinfo/{spuId}")
    public Resp<List<SkuInfoEntity>> querySkuBySpuId(@PathVariable("spuId")Long spuId);

    //根据randId查询品牌
    @GetMapping("pms/brand/info/{brandId}")
    public Resp<BrandEntity> queryBrandById(@PathVariable("brandId") Long brandId);

    //根据categoryId查询分类
    @GetMapping("pms/category/info/{catId}")
    public Resp<CategoryEntity> queryCategoryById(@PathVariable("catId") Long catId);

    //根据skuId检索属性
    @GetMapping("pms/productattrvalue/{spuId}")
    public Resp<List<SpuAttributeValueVO>> querySearchAttrValue(@PathVariable("spuId")Long spuId);

    @GetMapping("pms/category")
    public Resp<List<CategoryEntity>> queryCategories(@RequestParam(value = "level" , defaultValue = "0")Integer level , @RequestParam(value = "parentCid" , required = false)Long parentCid);

    @GetMapping("pms/category/{pid}")
    public Resp<List<CategoryVO>> queryCateGoryWithSub(@PathVariable("pid")Long pid);

    @GetMapping("pms/skuinfo/info/{skuId}")
    public Resp<SkuInfoEntity> querySkuById(@PathVariable("skuId") Long skuId);

    @GetMapping("pms/spuinfo/info/{id}")
    public Resp<SpuInfoEntity> querySpuById(@PathVariable("id") Long id);

    @GetMapping("pms/skuimages/{skuId}")
    public Resp<List<String>> queryPicsBySkuId(@PathVariable("skuId")Long skuId);

    @GetMapping("pms/skusaleattrvalue/{spuId}")
    public Resp<List<SkuSaleAttrValueEntity>> querySaleAttrValue(@PathVariable("spuId")Long spuId);

    @GetMapping("pms/spuinfodesc/info/{spuId}")
    public Resp<SpuInfoDescEntity> queryDescById(@PathVariable("spuId") Long spuId);

    @GetMapping("pms/attrgroup/item/group/{cid}/{spuId}")
    public Resp<List<GroupVO>> queryGroupVOByCid(@PathVariable("cid")Long cid , @PathVariable("spuId")Long spuId);
}
