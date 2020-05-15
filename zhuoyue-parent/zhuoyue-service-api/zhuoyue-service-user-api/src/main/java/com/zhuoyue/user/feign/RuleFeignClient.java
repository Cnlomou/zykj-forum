package com.zhuoyue.user.feign;

import com.zhuoyue.user.pojo.Rule;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "user")
@RequestMapping("/rule")
public interface RuleFeignClient {

    @PostMapping("/search")
    Result<List<Rule>> findByInfo(@RequestBody Rule rule);

    @PostMapping("/search/{pageNum}/{pageSize}")
    Result<PageInfo<Rule>> findAllByPage(@RequestBody Rule rule,
                                         @PathVariable(name = "pageNum") Integer num,
                                         @PathVariable(name = "pageSize") Integer size);

    @GetMapping("/search/{pageNum}/{pageSize}")
    Result<PageInfo<Rule>> findAllByPage(@PathVariable(name = "pageNum") Integer num,
                                         @PathVariable(name = "pageSize") Integer size);

    @PostMapping("/")
    Result add(@RequestBody Rule rule);

    @DeleteMapping("/{id}")
    Result del(@PathVariable(name = "id") Integer id);

    @PutMapping("/{id}")
    Result update(@PathVariable(name = "id") Integer id, @RequestBody Rule rule);

    @GetMapping("/search/{id}")
    Result<Rule> findById(@PathVariable(name = "id") Integer id);

    @GetMapping("/search")
    Result<List<Rule>> findAll();
}