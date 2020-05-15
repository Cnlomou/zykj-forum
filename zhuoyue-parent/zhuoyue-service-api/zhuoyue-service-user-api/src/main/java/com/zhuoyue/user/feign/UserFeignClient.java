package com.zhuoyue.user.feign;

import com.zhuoyue.user.pojo.User;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "user")
@RequestMapping("/user")
public interface UserFeignClient {

    @PutMapping("/increment/{id}")
    Result updateArticleCount(@PathVariable(name = "id") Long id);

    @PostMapping("/login")
    Result<String> login(@RequestParam String username, @RequestParam String password);

    @DeleteMapping("/logout")
    Result logout(@RequestBody String jwt);

    @PostMapping("/search")
    Result<List<User>> findByInfo(@RequestBody User user);

    @PostMapping("/search/{pageNum}/{pageSize}")
    Result<PageInfo<User>> findAllByPage(@RequestBody User user,
                                         @PathVariable(name = "pageNum") Integer num,
                                         @PathVariable(name = "pageSize") Integer size);

    @GetMapping("/search/{pageNum}/{pageSize}")
    Result<PageInfo<User>> findAllByPage(@PathVariable(name = "pageNum") Integer num,
                                         @PathVariable(name = "pageSize") Integer size);

    @PostMapping("/")
    Result add(@RequestBody User user);

    @DeleteMapping("/{id}")
    Result del(@PathVariable(name = "id") Long id);

    @PutMapping("/{id}")
    Result update(@PathVariable(name = "id") Long id, @RequestBody User user);

    @GetMapping("/search/{id}")
    Result<User> findById(@PathVariable(name = "id") Long id);

    @GetMapping("/search")
    Result<List<User>> findAll();
}