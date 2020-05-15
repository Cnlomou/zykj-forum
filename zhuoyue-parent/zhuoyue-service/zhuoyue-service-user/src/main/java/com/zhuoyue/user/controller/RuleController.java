package com.zhuoyue.user.controller;

import com.zhuoyue.user.pojo.Rule;
import com.zhuoyue.user.service.RuleService;
import entity.Result;
import entity.StatusCode;

import java.util.List;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rule")
@CrossOrigin
@Api
public class RuleController {
    @Autowired
    private RuleService ruleService;

    @PostMapping("/search")
    public Result<List<Rule>> findByInfo(Rule rule) {
        return new Result<>(true, StatusCode.OK, "按信息查询成功", ruleService.findByInfo(rule));
    }

    @PostMapping("/search/{pageNum}/{pageSize}")
    public Result<PageInfo<Rule>> findAllByPage(Rule rule,
                                                @PathVariable(name = "pageNum") Integer num,
                                                @PathVariable(name = "pageSize") Integer size) {
        return new Result<>(true, StatusCode.OK, "按信息查询并分页成功", ruleService.findInfoByPage(rule, num, size));
    }

    @GetMapping("/search/{pageNum}/{pageSize}")
    public Result<PageInfo<Rule>> findAllByPage(@PathVariable(name = "pageNum") Integer num,
                                                @PathVariable(name = "pageSize") Integer size) {
        return new Result<>(true, StatusCode.OK, "查询所有并分页成功", ruleService.findAllByPage(num, size));
    }

    @PostMapping
    public Result add(Rule rule) {
        ruleService.add(rule);
        return new Result<>(true, StatusCode.OK, "添加数据成功");
    }

    @DeleteMapping("/{id}")
    public Result del(@PathVariable(name = "id") Integer id) {
        ruleService.delById(id);
        return new Result<>(true, StatusCode.OK, "删除数据成功");
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable(name = "id") Integer id, Rule rule) {
        rule.setId(id);
        ruleService.update(rule);
        return new Result<>(true, StatusCode.OK, "修改数据成功");
    }

    @GetMapping("/search/{id}")
    public Result<Rule> findById(@PathVariable(name = "id") Integer id) {
        return new Result<>(true, StatusCode.OK, "按主键查找成功", ruleService.findById(id));
    }

    @GetMapping("/search")
    public Result<List<Rule>> findAll() {
        return new Result<>(true, StatusCode.OK, "查找所有数据成功", ruleService.findAll());
    }
}