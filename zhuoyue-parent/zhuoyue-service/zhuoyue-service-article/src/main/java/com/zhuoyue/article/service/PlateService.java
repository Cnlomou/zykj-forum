package com.zhuoyue.article.service;

import com.github.pagehelper.PageInfo;

import java.util.List;

import com.zhuoyue.article.pojo.Plate;
import com.zhuoyue.article.pojo.PlatePair;

public interface PlateService {
    boolean incrementArticleCount(Integer id);

    void add(Plate plate);

    List<PlatePair> getRecommendPlate(Integer size);

    void delById(Integer id);

    void update(Plate plate);

    Plate findById(Integer id);

    List<Plate> findAll();

    PageInfo<Plate> findAllByPage(Integer pageNum, Integer pageSize);

    List<Plate> findByInfo(Plate plate);

    PageInfo<Plate> findInfoByPage(Plate plate, Integer pageNum, Integer pageSize);
}