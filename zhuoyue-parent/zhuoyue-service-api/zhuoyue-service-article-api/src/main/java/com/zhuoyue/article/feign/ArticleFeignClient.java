package com.zhuoyue.article.feign;

import com.github.pagehelper.PageInfo;
import com.zhuoyue.article.pojo.Article;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "article")
@RequestMapping("/article")
public interface ArticleFeignClient {

    @GetMapping("/search/page/{num}/{size}")
    Result<PageInfo<Article>> findArticleByPlateId(Integer id,
                                                   @PathVariable(name = "num") Integer num,
                                                   @PathVariable(name = "size") Integer size);

    @GetMapping("/visit/{id}")
    Result addVisit(@PathVariable(name = "id") Long id);

    @PostMapping("/search")
    Result<List<Article>> findByInfo(@RequestBody Article article);

    @PostMapping("/search/{pageNum}/{pageSize}")
    Result<PageInfo<Article>> findAllByPage(@RequestBody Article article,
                                            @PathVariable(name = "pageNum") Integer num,
                                            @PathVariable(name = "pageSize") Integer size);

    @GetMapping("/search/{pageNum}/{pageSize}")
    Result<PageInfo<Article>> findAllByPage(@PathVariable(name = "pageNum") Integer num,
                                            @PathVariable(name = "pageSize") Integer size);

    @PostMapping("/")
    Result add(@RequestBody Article article);

    @DeleteMapping("/{id}")
    Result del(@PathVariable(name = "id") Long id);

    @PutMapping("/{id}")
    Result update(@PathVariable(name = "id") Long id, @RequestBody Article article);

    @GetMapping("/search/{id}")
    Result<Article> findById(@PathVariable(name = "id") Long id);

    @GetMapping("/search")
    Result<List<Article>> findAll();
}