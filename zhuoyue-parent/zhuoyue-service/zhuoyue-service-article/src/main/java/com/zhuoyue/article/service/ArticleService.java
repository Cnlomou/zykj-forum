package com.zhuoyue.article.service;

import com.github.pagehelper.PageInfo;

import java.util.List;

import com.zhuoyue.article.pojo.Article;
import io.swagger.models.auth.In;

public interface ArticleService {
    PageInfo<Article> findByPlateId(Integer id, Integer num, Integer size);

    void addVisit(Long id);

    void add(Article article);

    void delById(Long id);

    void delByStatus(Long id);

    void update(Article article);

    Article findById(Long id);

    List<Article> findAll();

    PageInfo<Article> findAllByPage(Integer pageNum, Integer pageSize);

    List<Article> findByInfo(Article article);

    PageInfo<Article> findInfoByPage(Article article, Integer pageNum, Integer pageSize);

}