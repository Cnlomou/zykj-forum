package com.zhuoyue.search.controller;

import com.zhuoyue.search.service.ArticleService;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Linmo
 * @create 2020/4/19 13:22
 */
@RestController
@CrossOrigin
@RequestMapping("/search")
@Api
public class ArticleSearchController {

    @Autowired
    private ArticleService articleService;

    @PutMapping("/import/all")
    @ApiOperation(value = "导入所有数据")
    public Result importDataAll() {
        String resutl = articleService.importDateAll();
        return new Result(true, StatusCode.OK, resutl);
    }

    @PutMapping("/import/{pageNum}/{pageSize}")
    @ApiOperation(value = "分页的方式导入数据")
    public Result importData(@PathVariable(name = "pageNum") Integer num,
                             @PathVariable(name = "pageSize") Integer size) {
        String resutl = articleService.importData(num, size);
        return new Result(true, StatusCode.OK, resutl);
    }


    @GetMapping("/params")
    @ApiOperation(value = "默认的查询渲染")
    public Result<Map<String, Object>> searchArticle(@RequestParam Map<String, String> params) {
        Map<String, Object> stringObjectMap = articleService.searchArticle(params);
        return new Result<>(true, StatusCode.OK, "查询数据成功", stringObjectMap);
    }

    @GetMapping("/keyword")
    @ApiOperation(value = "根据关键词查询")
    public Result<Map<String, ?>> keyWordSearch(@RequestParam Map<String, String> params) {

        Map<String, ?> map = articleService.keyWordSearch(params);
        return new Result<>(true, StatusCode.OK, "按关键字查询成功", map);
    }
}
