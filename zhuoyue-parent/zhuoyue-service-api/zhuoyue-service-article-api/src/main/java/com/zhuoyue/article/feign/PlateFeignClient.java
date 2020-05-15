package com.zhuoyue.article.feign;

import com.github.pagehelper.PageInfo;
import com.zhuoyue.article.pojo.Plate;
import com.zhuoyue.article.pojo.PlatePair;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "article")
@RequestMapping("/plate")
public interface PlateFeignClient {

//    @PutMapping("/increment/{id}")
//    Result updateArticleCount(@PathVariable(name = "id") Integer id);

    @GetMapping("/search/recommend")
    Result<List<PlatePair>> recommendPlate();

    @PostMapping("/search")
    Result<List<Plate>> findByInfo(@RequestBody Plate plate);

    @PostMapping("/search/{pageNum}/{pageSize}")
    Result<PageInfo<Plate>> findAllByPage(@RequestBody Plate plate,
                                          @PathVariable(name = "pageNum") Integer num,
                                          @PathVariable(name = "pageSize") Integer size);

    @GetMapping("/search/{pageNum}/{pageSize}")
    Result<PageInfo<Plate>> findAllByPage(@PathVariable(name = "pageNum") Integer num,
                                          @PathVariable(name = "pageSize") Integer size);

    @PostMapping("/")
    Result add(@RequestBody Plate plate);

    @DeleteMapping("/{id}")
    Result del(@PathVariable(name = "id") Integer id);

    @PutMapping("/{id}")
    Result update(@PathVariable(name = "id") Integer id, @RequestBody Plate plate);

    @GetMapping("/search/{id}")
    Result<Plate> findById(@PathVariable(name = "id") Integer id);

    @GetMapping("/search")
    Result<List<Plate>> findAll();
}