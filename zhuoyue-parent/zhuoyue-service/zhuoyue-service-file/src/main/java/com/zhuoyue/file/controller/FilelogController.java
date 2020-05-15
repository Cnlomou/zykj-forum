package com.zhuoyue.file.controller;

import com.github.pagehelper.PageInfo;
import com.zhuoyue.file.pojo.Filelog;
import com.zhuoyue.file.service.FilelogService;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/filelog")
@CrossOrigin
@Api
public class FilelogController {
    @Autowired
    private FilelogService filelogService;

    @GetMapping("/search/time/{pageNum}/{pageSize}")
    public Result<PageInfo<Filelog>> findByDate(Date bg, Date lt, @PathVariable(name = "pageNum") Integer num,
                                                @PathVariable(name = "pageSize") Integer size) {
        PageInfo<Filelog> byTime = filelogService.getByTime(bg, lt, num, size);
        return new Result<>(true, StatusCode.OK, "按时间查找成功", byTime);
    }

    @PostMapping("/search")
    public Result<List<Filelog>> findByInfo(Filelog filelog) {
        return new Result<>(true, StatusCode.OK, "按信息查询成功", filelogService.findByInfo(filelog));
    }

    @PostMapping("/search/{pageNum}/{pageSize}")
    public Result<PageInfo<Filelog>> findAllByPage(Filelog filelog,
                                                   @PathVariable(name = "pageNum") Integer num,
                                                   @PathVariable(name = "pageSize") Integer size) {
        return new Result<>(true, StatusCode.OK, "按信息查询并分页成功", filelogService.findInfoByPage(filelog, num, size));
    }

    @GetMapping("/search/{pageNum}/{pageSize}")
    public Result<PageInfo<Filelog>> findAllByPage(@PathVariable(name = "pageNum") Integer num,
                                                   @PathVariable(name = "pageSize") Integer size) {
        return new Result<>(true, StatusCode.OK, "查询所有并分页成功", filelogService.findAllByPage(num, size));
    }

    @DeleteMapping("/{id}")
    public Result del(@PathVariable(name = "id") Long id) {
        filelogService.delById(id);
        return new Result<>(true, StatusCode.OK, "删除数据成功");
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable(name = "id") Long id, Filelog filelog) {
        filelog.setId(id);
        filelogService.update(filelog);
        return new Result<>(true, StatusCode.OK, "修改数据成功");
    }

    @GetMapping("/search/{id}")
    public Result<Filelog> findById(@PathVariable(name = "id") Long id) {
        return new Result<>(true, StatusCode.OK, "按主键查找成功", filelogService.findById(id));
    }

    @GetMapping("/search")
    public Result<List<Filelog>> findAll() {
        return new Result<>(true, StatusCode.OK, "查找所有数据成功", filelogService.findAll());
    }
}