package com.atguigu.gmall.item.service;

import com.atguigu.core.bean.Resp;
import com.atguigu.gmall.item.feign.GmallPmsClient;
import com.atguigu.gmall.item.feign.GmallSmsClient;
import com.atguigu.gmall.item.feign.GmallWmsClient;
import com.atguigu.gmall.item.vo.ItemVO;
import com.atguigu.gmall.pms.entity.*;
import com.atguigu.gmall.pms.vo.GroupVO;
import com.atguigu.gmall.sms.vo.ItemSaleVO;
import com.atguigu.gmall.wms.entity.WareSkuEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author feilong
 * @create 2019-11-11 20:47
 */
@Service
public class ItemService {

    @Autowired
    private GmallPmsClient gmallPmsClient;

    @Autowired
    private GmallWmsClient gmallWmsClient;

    @Autowired
    private GmallSmsClient gmallSmsClient;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;


    public ItemVO item(Long skuId) {
        ItemVO itemVO = new ItemVO();
        //1.查询sku信息
        CompletableFuture<SkuInfoEntity> skuCompletableFuture = CompletableFuture.supplyAsync(() -> {
            Resp<SkuInfoEntity> skuInfoEntityResp = this.gmallPmsClient.querySkuById(skuId);
            SkuInfoEntity skuInfoEntity = skuInfoEntityResp.getData();
            BeanUtils.copyProperties(skuInfoEntity, itemVO);
            return skuInfoEntity;
        }, threadPoolExecutor);

        CompletableFuture<Void> brandCompletableFuture = skuCompletableFuture.thenAcceptAsync(skuInfoEntity -> {
            //2.品牌
            Resp<BrandEntity> brandEntityResp = this.gmallPmsClient.queryBrandById(skuInfoEntity.getBrandId());
            itemVO.setBrand(brandEntityResp.getData());
        }, threadPoolExecutor);

        CompletableFuture<Void> catgroyCompletableFuture = skuCompletableFuture.thenAcceptAsync(skuInfoEntity -> {
            //3.分类
            Resp<CategoryEntity> categoryEntityResp = this.gmallPmsClient.queryCategoryById(skuInfoEntity.getCatalogId());
            itemVO.setCategory(categoryEntityResp.getData());
        }, threadPoolExecutor);

        CompletableFuture<Void> spuCompletableFuture = skuCompletableFuture.thenAcceptAsync(skuInfoEntity -> {
            //4.spu信息
            Resp<SpuInfoEntity> spuInfoEntityResp = this.gmallPmsClient.querySpuById(skuInfoEntity.getSpuId());
            itemVO.setSpuInfo(spuInfoEntityResp.getData());
        }, threadPoolExecutor);


        //5.设置图片信息
        CompletableFuture<Void> picCompletableFuture = CompletableFuture.runAsync(() -> {
            Resp<List<String>> picsResp = this.gmallPmsClient.queryPicsBySkuId(skuId);
            itemVO.setPics(picsResp.getData());
        }, threadPoolExecutor);

        //6.营销信息
        CompletableFuture<Void> saleCompletableFuture = CompletableFuture.runAsync(() -> {
            Resp<List<ItemSaleVO>> itemSaleResp = this.gmallSmsClient.queryItemSaleVOs(skuId);
            itemVO.setSales(itemSaleResp.getData());
        }, threadPoolExecutor);

        //7.是否有货
        CompletableFuture<Void> storeCompletableFuture = CompletableFuture.runAsync(() -> {
            Resp<List<WareSkuEntity>> wareSkuResp = this.gmallWmsClient.queryWareBySkuId(skuId);
            List<WareSkuEntity> wareSkuEntities = wareSkuResp.getData();
            itemVO.setStore(wareSkuEntities.stream().anyMatch(t -> t.getStock() > 0));
        }, threadPoolExecutor);

        //8.spu所有的销售属性
        CompletableFuture<Void> spuSaleCompletableFuture = skuCompletableFuture.thenAcceptAsync(skuInfoEntity -> {
            Resp<List<SkuSaleAttrValueEntity>> saleAttrValueResp = this.gmallPmsClient.querySaleAttrValue(skuInfoEntity.getSpuId());
            itemVO.setSkuSales(saleAttrValueResp.getData());
        }, threadPoolExecutor);


        //9.spu的描述信息
        CompletableFuture<Void> descCompletableFuture = skuCompletableFuture.thenAcceptAsync(skuInfoEntity -> {
            Resp<SpuInfoDescEntity> spuInfoDescEntityResp = this.gmallPmsClient.queryDescById(skuInfoEntity.getSpuId());
            itemVO.setDesc(spuInfoDescEntityResp.getData());
        }, threadPoolExecutor);

        //10.规格属性分组及组下的信息
        CompletableFuture<Void> groupCompletableFuture = skuCompletableFuture.thenAcceptAsync(skuInfoEntity -> {
            Resp<List<GroupVO>> listResp = this.gmallPmsClient.queryGroupVOByCid(skuInfoEntity.getCatalogId(), skuInfoEntity.getSpuId());
            itemVO.setGroups(listResp.getData());
        }, threadPoolExecutor);

        CompletableFuture.allOf(brandCompletableFuture , catgroyCompletableFuture , storeCompletableFuture , saleCompletableFuture
        , picCompletableFuture , spuCompletableFuture , descCompletableFuture , spuSaleCompletableFuture , groupCompletableFuture).join();
        return itemVO;
    }
}
