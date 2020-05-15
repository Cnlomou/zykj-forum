package com.zhuoyue.article.controller;

import com.zhuoyue.article.pojo.Plate;
import com.zhuoyue.article.pojo.PlatePair;
import com.zhuoyue.article.service.PlateService;
import entity.Result;
import entity.StatusCode;

import java.util.List;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apiguardian.api.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plate")
@CrossOrigin
@Api
public class PlateController {
    @Autowired
    private PlateService plateService;

//    @PutMapping("/increment/{id}")
//    public Result updateArticleCount(@PathVariable(name = "id") Integer id) {
//        if (plateService.incrementArticleCount(id))
//            return new Result(true, StatusCode.OK, "更新板块下的文章数成功");
//        return new Result(false, StatusCode.ERROR, "更新板块下的文章数失败");
//    }

    @PostMapping("/search")
    public Result<List<Plate>> findByInfo(Plate plate) {
        return new Result<>(true, StatusCode.OK, "按信息查询成功", plateService.findByInfo(plate));
    }

    @GetMapping("/search/recommend")
    public Result<List<PlatePair>> recommendPlate() {
        List<PlatePair> recommendPlate = plateService.getRecommendPlate(4);//默认为四个推荐板块
        return new Result<>(true, StatusCode.OK, "推荐板块获取成功", recommendPlate);
    }

    @PostMapping("/search/{pageNum}/{pageSize}")
    public Result<PageInfo<Plate>> findAllByPage(Plate plate,
                                                 @PathVariable(name = "pageNum") Integer num,
                                                 @PathVariable(name = "pageSize") Integer size) {
        return new Result<>(true, StatusCode.OK, "按信息查询并分页成功", plateService.findInfoByPage(plate, num, size));
    }

    @GetMapping("/search/{pageNum}/{pageSize}")
    public Result<PageInfo<Plate>> findAllByPage(@PathVariable(name = "pageNum") Integer num,
                                                 @PathVariable(name = "pageSize") Integer size) {
        return new Result<>(true, StatusCode.OK, "查询所有并分页成功", plateService.findAllByPage(num, size));
    }

    @PostMapping
    public Result add(Plate plate) {
        plateService.add(plate);
        return new Result<>(true, StatusCode.OK, "添加数据成功");
    }

    @DeleteMapping("/{id}")
    public Result del(@PathVariable(name = "id") Integer id) {
        plateService.delById(id);
        return new Result<>(true, StatusCode.OK, "删除数据成功");
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable(name = "id") Integer id, Plate plate) {
        plate.setId(id);
        plateService.update(plate);
        return new Result<>(true, StatusCode.OK, "修改数据成功");
    }

    @GetMapping("/search/{id}")
    public Result<Plate> findById(@PathVariable(name = "id") Integer id) {
        return new Result<>(true, StatusCode.OK, "按主键查找成功", plateService.findById(id));
    }

    @GetMapping("/search")
    public Result<List<Plate>> findAll() {
        return new Result<>(true, StatusCode.OK, "查找所有数据成功", plateService.findAll());
    }
}