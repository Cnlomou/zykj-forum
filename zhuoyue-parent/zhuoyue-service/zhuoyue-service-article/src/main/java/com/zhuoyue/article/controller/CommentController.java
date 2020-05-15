package com.zhuoyue.article.controller;

import com.zhuoyue.article.dao.ArticleMapper;
import com.zhuoyue.article.pojo.Comment;
import com.zhuoyue.article.service.CommentService;
import entity.IdWorker;
import entity.Result;
import entity.StatusCode;

import java.util.List;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@CrossOrigin
@Api
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private IdWorker idWorker;

    @GetMapping("/search/art/{id}")
    public Result<List<Comment>> findByArtId(@PathVariable(name = "id") Long id) {
        List<Comment> byArticle = commentService.findByArticle(id);
        return new Result<>(true, StatusCode.OK, "根据articleId查找成功", byArticle);
    }

    @PostMapping("/search")
    public Result<List<Comment>> findByInfo(Comment comment) {
        return new Result<>(true, StatusCode.OK, "按信息查询成功", commentService.findByInfo(comment));
    }

    @PostMapping("/search/{pageNum}/{pageSize}")
    public Result<PageInfo<Comment>> findAllByPage(Comment comment,
                                                   @PathVariable(name = "pageNum") Integer num,
                                                   @PathVariable(name = "pageSize") Integer size) {
        return new Result<>(true, StatusCode.OK, "按信息查询并分页成功", commentService.findInfoByPage(comment, num, size));
    }

    @GetMapping("/search/{pageNum}/{pageSize}")
    public Result<PageInfo<Comment>> findAllByPage(@PathVariable(name = "pageNum") Integer num,
                                                   @PathVariable(name = "pageSize") Integer size) {
        return new Result<>(true, StatusCode.OK, "查询所有并分页成功", commentService.findAllByPage(num, size));
    }

    @PostMapping
    public Result add(Comment comment) {
        comment.setId(idWorker.nextId());
        commentService.add(comment);
        return new Result<>(true, StatusCode.OK, "添加数据成功");
    }

    @DeleteMapping("/{id}")
    public Result del(@PathVariable(name = "id") Long id) {
        commentService.delById(id);
        return new Result<>(true, StatusCode.OK, "删除数据成功");
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable(name = "id") Long id, Comment comment) {
        comment.setId(id);
        commentService.update(comment);
        return new Result<>(true, StatusCode.OK, "修改数据成功");
    }

    @GetMapping("/search/{id}")
    public Result<Comment> findById(@PathVariable(name = "id") Long id) {
        return new Result<>(true, StatusCode.OK, "按主键查找成功", commentService.findById(id));
    }

    @GetMapping("/search")
    public Result<List<Comment>> findAll() {
        return new Result<>(true, StatusCode.OK, "查找所有数据成功", commentService.findAll());
    }
}