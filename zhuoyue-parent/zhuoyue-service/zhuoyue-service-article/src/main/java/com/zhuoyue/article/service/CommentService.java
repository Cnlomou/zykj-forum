package com.zhuoyue.article.service;

import com.github.pagehelper.PageInfo;

import java.util.List;

import com.zhuoyue.article.pojo.Comment;

public interface CommentService {
    void add(Comment comment);

    void delById(Long id);

    List<Comment> findByArticle(Long id);

    void delByArtId(Long id);

    void update(Comment comment);

    Comment findById(Long id);

    List<Comment> findAll();

    PageInfo<Comment> findAllByPage(Integer pageNum, Integer pageSize);

    List<Comment> findByInfo(Comment comment);

    PageInfo<Comment> findInfoByPage(Comment comment, Integer pageNum, Integer pageSize);
}