package com.zhuoyue.article.feign;

import com.github.pagehelper.PageInfo;
import com.zhuoyue.article.pojo.Recoment;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "article")
@RequestMapping("/recoment")
public interface RecomentFeignClient {

    @PostMapping("/search")
    Result<List<Recoment>> findByInfo(@RequestBody Recoment recoment);

    @PostMapping("/search/{pageNum}/{pageSize}")
    Result<PageInfo<Recoment>> findAllByPage(@RequestBody Recoment recoment,
                                             @PathVariable(name = "pageNum") Integer num,
                                             @PathVariable(name = "pageSize") Integer size);

    @GetMapping("/search/{pageNum}/{pageSize}")
    Result<PageInfo<Recoment>> findAllByPage(@PathVariable(name = "pageNum") Integer num,
                                             @PathVariable(name = "pageSize") Integer size);

    @PostMapping("/")
    Result add(@RequestBody Recoment recoment);

    @DeleteMapping("/{id}")
    Result del(@PathVariable(name = "id") Long id);

    @PutMapping("/{id}")
    Result update(@PathVariable(name = "id") Long id, @RequestBody Recoment recoment);

    @GetMapping("/search/{id}")
    Result<Recoment> findById(@PathVariable(name = "id") Long id);

    @GetMapping("/search")
    Result<List<Recoment>> findAll();
}