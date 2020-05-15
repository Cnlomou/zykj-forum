package com.zhuoyue.article.feign;

import com.github.pagehelper.PageInfo;
import com.zhuoyue.article.pojo.Comment;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "article")
@RequestMapping("/comment")
public interface CommentFeignClient {

    @GetMapping("/search/art/{id}")
    Result<List<Comment>> findByArtId(@PathVariable(name = "id") Long id);

    @PostMapping("/search")
    Result<List<Comment>> findByInfo(@RequestBody Comment comment);

    @PostMapping("/search/{pageNum}/{pageSize}")
    Result<PageInfo<Comment>> findAllByPage(@RequestBody Comment comment,
                                            @PathVariable(name = "pageNum") Integer num,
                                            @PathVariable(name = "pageSize") Integer size);

    @GetMapping("/search/{pageNum}/{pageSize}")
    Result<PageInfo<Comment>> findAllByPage(@PathVariable(name = "pageNum") Integer num,
                                            @PathVariable(name = "pageSize") Integer size);

    @PostMapping("/")
    Result add(@RequestBody Comment comment);

    @DeleteMapping("/{id}")
    Result del(@PathVariable(name = "id") Long id);

    @PutMapping("/{id}")
    Result update(@PathVariable(name = "id") Long id, @RequestBody Comment comment);

    @GetMapping("/search/{id}")
    Result<Comment> findById(@PathVariable(name = "id") Long id);

    @GetMapping("/search")
    Result<List<Comment>> findAll();
}