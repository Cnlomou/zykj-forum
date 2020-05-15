package com.zhuoyue.search.feign;

import entity.Result;
import feign.Param;
import feign.QueryMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Linmo
 * @create 2020/4/19 17:10
 */

@FeignClient(value = "search")
@RequestMapping("/search")
public interface ArticleSearchFeignClient {

    @PutMapping("/import/all")
    Result importDataAll();

    @PutMapping("/import/{pageNum}/{pageSize}")
    Result importData(@PathVariable(name = "pageNum") Integer num,
                      @PathVariable(name = "pageSize") Integer size);


    @RequestMapping(value = "/params", method = RequestMethod.GET)
    Result<Map<String, Object>> searchArticle(@RequestParam Map<String, String> params);

    @GetMapping("/keyword")
    Result<Map<String, ?>> keyWordSearch(@RequestParam Map<String, String> params);
}
