package com.zhuoyue.article.controller;

import com.zhuoyue.article.pojo.Article;
import com.zhuoyue.article.service.ArticleService;
import entity.IdWorker;
import entity.Result;
import entity.StatusCode;

import java.util.List;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@CrossOrigin
@Api
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private IdWorker idWorker;

    @GetMapping("/search/page/{num}/{size}")
    public Result<PageInfo<Article>> findArticleByPlateId(Integer id,
                                                          @PathVariable(name = "num") Integer num,
                                                          @PathVariable(name = "size") Integer size) {
        PageInfo<Article> byPlateId = articleService.findByPlateId(id, num, size);
        return new Result<>(true, StatusCode.OK, "根据板块id查找文章成功", byPlateId);
    }

    @GetMapping("/visit/{id}")
    @ApiOperation(value = "增加访问")
    public Result addVisit(@PathVariable(name = "id") Long id) {
        articleService.addVisit(id);
        return new Result(true, StatusCode.OK, "增加访问");
    }

    @PostMapping("/search")
    @ApiOperation(value = "根据信息查询", notes = "不适用主键或外键查询，通常查询结果不唯一")
    @ApiResponse(response = Result.class, code = 200, message = "按信息查询成功")
    public Result<List<Article>> findByInfo(Article article) {
        return new Result<>(true, StatusCode.OK, "按信息查询成功", articleService.findByInfo(article));
    }

    @PostMapping("/search/{pageNum}/{pageSize}")
    @ApiOperation(value = "根据信息查询并分页", notes = "不适用主键或外键查询，通常查询结果不唯一")
    public Result<PageInfo<Article>> findAllByPage(Article article,
                                                   @PathVariable(name = "pageNum") Integer num,
                                                   @PathVariable(name = "pageSize") Integer size) {
        return new Result<>(true, StatusCode.OK, "按信息查询并分页成功", articleService.findInfoByPage(article, num, size));
    }

    @GetMapping("/search/{pageNum}/{pageSize}")
    @ApiOperation(value = "分页查询所有的信息")
    public Result<PageInfo<Article>> findAllByPage(@PathVariable(name = "pageNum") Integer num,
                                                   @PathVariable(name = "pageSize") Integer size) {
        return new Result<>(true, StatusCode.OK, "查询所有并分页成功", articleService.findAllByPage(num, size));
    }

    @PostMapping
    @ApiOperation(value = "添加一个数据")
    public Result add(@RequestBody Article article) {
        article.setId(idWorker.nextId());
        articleService.add(article);
        return new Result<>(true, StatusCode.OK, "添加数据成功");
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据id删除一个数据")
    public Result del(@PathVariable(name = "id") Long id) {
        articleService.delById(id);
        return new Result<>(true, StatusCode.OK, "删除数据成功");
    }

    @DeleteMapping("/status/{id}")
    @ApiOperation(value = "以修改状态的方式删除一个数据")
    public Result delByStatus(@PathVariable(name = "id") Long id) {
        articleService.delByStatus(id);
        return new Result(true, StatusCode.OK, "已修改标志位达到删除");
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "修改一个数据")
    public Result update(@PathVariable(name = "id") Long id, Article article) {
        article.setId(id);
        articleService.update(article);
        return new Result<>(true, StatusCode.OK, "修改数据成功");
    }

    @GetMapping("/search/{id}")
    @ApiOperation(value = "以id查询一个数据")
    public Result<Article> findById(@PathVariable(name = "id") Long id) {
        return new Result<>(true, StatusCode.OK, "按主键查找成功", articleService.findById(id));
    }

    @GetMapping("/search")
    @ApiOperation(value = "查询所有数据", notes = "数据量多的时候慎用")
    public Result<List<Article>> findAll() {
        return new Result<>(true, StatusCode.OK, "查找所有数据成功", articleService.findAll());
    }
}