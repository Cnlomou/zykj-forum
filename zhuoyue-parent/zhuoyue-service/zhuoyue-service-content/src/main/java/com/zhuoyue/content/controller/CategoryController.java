package com.zhuoyue.content.controller;

import com.github.pagehelper.PageInfo;
import com.zhuoyue.content.pojo.Category;
import com.zhuoyue.content.service.CategoryService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/search")
    public Result<List<Category>> findByInfo(Category category) {
        return new Result<>(true, StatusCode.OK, "按信息查询成功", categoryService.findByInfo(category));
    }

    @PostMapping("/search/{pageNum}/{pageSize}")
    public Result<PageInfo<Category>> findAllByPage(Category category,
                                                    @PathVariable(name = "pageNum") Integer num,
                                                    @PathVariable(name = "pageSize") Integer size) {
        return new Result<>(true, StatusCode.OK, "按信息查询并分页成功", categoryService.findInfoByPage(category, num, size));
    }

    @GetMapping("/search/{pageNum}/{pageSize}")
    public Result<PageInfo<Category>> findAllByPage(@PathVariable(name = "pageNum") Integer num,
                                                    @PathVariable(name = "pageSize") Integer size) {
        return new Result<>(true, StatusCode.OK, "查询所有并分页成功", categoryService.findAllByPage(num, size));
    }

    @PostMapping
    public Result add(Category category) {
        categoryService.add(category);
        return new Result<>(true, StatusCode.OK, "添加数据成功");
    }

    @DeleteMapping("/{id}")
    public Result del(@PathVariable(name = "id") Integer id) {
        categoryService.delById(id);
        return new Result<>(true, StatusCode.OK, "删除数据成功");
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable(name = "id") Integer id, Category category) {
        category.setId(id);
        categoryService.update(category);
        return new Result<>(true, StatusCode.OK, "修改数据成功");
    }

    @GetMapping("/search/{id}")
    public Result<Category> findById(@PathVariable(name = "id") Integer id) {
        return new Result<>(true, StatusCode.OK, "按主键查找成功", categoryService.findById(id));
    }

    @GetMapping("/search")
    public Result<List<Category>> findAll() {
        return new Result<>(true, StatusCode.OK, "查找所有数据成功", categoryService.findAll());
    }
}