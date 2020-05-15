package com.zhuoyue.content.feign;

import com.github.pagehelper.PageInfo;
import com.zhuoyue.content.pojo.Content;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "content")
@RequestMapping("/content")
public interface ContentFeignClient {

    @PostMapping("/search")
    Result<List<Content>> findByInfo(@RequestBody Content content);

    @PostMapping("/search/{pageNum}/{pageSize}")
    Result<PageInfo<Content>> findAllByPage(@RequestBody Content content,
                                            @PathVariable(name = "pageNum") Integer num,
                                            @PathVariable(name = "pageSize") Integer size);

    @GetMapping("/search/{pageNum}/{pageSize}")
    Result<PageInfo<Content>> findAllByPage(@PathVariable(name = "pageNum") Integer num,
                                            @PathVariable(name = "pageSize") Integer size);

    @PostMapping("/")
    Result add(@RequestBody Content content);

    @DeleteMapping("/{id}")
    Result del(@PathVariable(name = "id") Integer id);

    @PutMapping("/{id}")
    Result update(@PathVariable(name = "id") Integer id, @RequestBody Content content);

    @GetMapping("/search/{id}")
    Result<Content> findById(@PathVariable(name = "id") Integer id);

    @GetMapping("/search")
    Result<List<Content>> findAll();
}