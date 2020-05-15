package com.zhuoyue.content.feign;

import com.github.pagehelper.PageInfo;
import com.zhuoyue.content.pojo.Category;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "content")
@RequestMapping("/category")
public interface CategoryFeignClient {

    @PostMapping("/search")
    Result<List<Category>> findByInfo(@RequestBody Category category);

    @PostMapping("/search/{pageNum}/{pageSize}")
    Result<PageInfo<Category>> findAllByPage(@RequestBody Category category,
                                             @PathVariable(name = "pageNum") Integer num,
                                             @PathVariable(name = "pageSize") Integer size);

    @GetMapping("/search/{pageNum}/{pageSize}")
    Result<PageInfo<Category>> findAllByPage(@PathVariable(name = "pageNum") Integer num,
                                             @PathVariable(name = "pageSize") Integer size);

    @PostMapping("/")
    Result add(@RequestBody Category category);

    @DeleteMapping("/{id}")
    Result del(@PathVariable(name = "id") Integer id);

    @PutMapping("/{id}")
    Result update(@PathVariable(name = "id") Integer id, @RequestBody Category category);

    @GetMapping("/search/{id}")
    Result<Category> findById(@PathVariable(name = "id") Integer id);

    @GetMapping("/search")
    Result<List<Category>> findAll();
}