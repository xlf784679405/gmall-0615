package com.atguigu.gmall.search.controller;

import com.atguigu.core.bean.Resp;
import com.atguigu.gmall.search.VO.SearchParamVO;
import com.atguigu.gmall.search.VO.SearchResponse;
import com.atguigu.gmall.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author feilong
 * @create 2019-11-06 10:53
 */
@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping
    public Resp<Object> search(SearchParamVO searchParamVO){
        SearchResponse response = this.searchService.search(searchParamVO);

        return Resp.ok(response);
    }
}
