package com.zhuoyue.content.controller;

import com.github.pagehelper.PageInfo;
import com.zhuoyue.content.pojo.Content;
import com.zhuoyue.content.service.ContentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content")
@CrossOrigin
public class ContentController {
    @Autowired
    private ContentService contentService;

    @PostMapping("/search")
    public Result<List<Content>> findByInfo(Content content) {
        return new Result<>(true, StatusCode.OK, "按信息查询成功", contentService.findByInfo(content));
    }

    @PostMapping("/search/{pageNum}/{pageSize}")
    public Result<PageInfo<Content>> findAllByPage(Content content,
                                                   @PathVariable(name = "pageNum") Integer num,
                                                   @PathVariable(name = "pageSize") Integer size) {
        return new Result<>(true, StatusCode.OK, "按信息查询并分页成功", contentService.findInfoByPage(content, num, size));
    }

    @GetMapping("/search/{pageNum}/{pageSize}")
    public Result<PageInfo<Content>> findAllByPage(@PathVariable(name = "pageNum") Integer num,
                                                   @PathVariable(name = "pageSize") Integer size) {
        return new Result<>(true, StatusCode.OK, "查询所有并分页成功", contentService.findAllByPage(num, size));
    }

    @PostMapping
    public Result add(Content content) {
        contentService.add(content);
        return new Result<>(true, StatusCode.OK, "添加数据成功");
    }

    @DeleteMapping("/{id}")
    public Result del(@PathVariable(name = "id") Integer id) {
        contentService.delById(id);
        return new Result<>(true, StatusCode.OK, "删除数据成功");
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable(name = "id") Integer id, Content content) {
        content.setId(id);
        contentService.update(content);
        return new Result<>(true, StatusCode.OK, "修改数据成功");
    }

    @GetMapping("/search/{id}")
    public Result<Content> findById(@PathVariable(name = "id") Integer id) {
        return new Result<>(true, StatusCode.OK, "按主键查找成功", contentService.findById(id));
    }

    @GetMapping("/search")
    public Result<List<Content>> findAll() {
        return new Result<>(true, StatusCode.OK, "查找所有数据成功", contentService.findAll());
    }
}