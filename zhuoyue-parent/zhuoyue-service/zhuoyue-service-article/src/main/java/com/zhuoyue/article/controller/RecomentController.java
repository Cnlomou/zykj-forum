package com.zhuoyue.article.controller;

import com.zhuoyue.article.pojo.Recoment;
import com.zhuoyue.article.service.RecomentService;
import entity.IdWorker;
import entity.Result;
import entity.StatusCode;

import java.util.List;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recoment")
@CrossOrigin
@Api
public class RecomentController {
    @Autowired
    private RecomentService recomentService;
    @Autowired
    private IdWorker idWorker;

    @PostMapping("/search")
    public Result<List<Recoment>> findByInfo(Recoment recoment) {
        return new Result<>(true, StatusCode.OK, "按信息查询成功", recomentService.findByInfo(recoment));
    }

    @PostMapping("/search/{pageNum}/{pageSize}")
    public Result<PageInfo<Recoment>> findAllByPage(Recoment recoment,
                                                    @PathVariable(name = "pageNum") Integer num,
                                                    @PathVariable(name = "pageSize") Integer size) {
        return new Result<>(true, StatusCode.OK, "按信息查询并分页成功", recomentService.findInfoByPage(recoment, num, size));
    }

    @GetMapping("/search/{pageNum}/{pageSize}")
    public Result<PageInfo<Recoment>> findAllByPage(@PathVariable(name = "pageNum") Integer num,
                                                    @PathVariable(name = "pageSize") Integer size) {
        return new Result<>(true, StatusCode.OK, "查询所有并分页成功", recomentService.findAllByPage(num, size));
    }

    @PostMapping
    public Result add(Recoment recoment) {
        recoment.setId(idWorker.nextId());
        recomentService.add(recoment);
        return new Result<>(true, StatusCode.OK, "添加数据成功");
    }

    @DeleteMapping("/{id}")
    public Result del(@PathVariable(name = "id") Long id) {
        recomentService.delById(id);
        return new Result<>(true, StatusCode.OK, "删除数据成功");
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable(name = "id") Long id, Recoment recoment) {
        recoment.setId(id);
        recomentService.update(recoment);
        return new Result<>(true, StatusCode.OK, "修改数据成功");
    }

    @GetMapping("/search/{id}")
    public Result<Recoment> findById(@PathVariable(name = "id") Long id) {
        return new Result<>(true, StatusCode.OK, "按主键查找成功", recomentService.findById(id));
    }

    @GetMapping("/search")
    public Result<List<Recoment>> findAll() {
        return new Result<>(true, StatusCode.OK, "查找所有数据成功", recomentService.findAll());
    }
}