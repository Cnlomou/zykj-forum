package com.zhuoyue.article.service;

import com.github.pagehelper.PageInfo;

import java.util.List;

import com.zhuoyue.article.pojo.Recoment;

public interface RecomentService {
    void add(Recoment recoment);

    void delById(Long id);

    void update(Recoment recoment);

    Recoment findById(Long id);

    List<Recoment> findAll();

    PageInfo<Recoment> findAllByPage(Integer pageNum, Integer pageSize);

    List<Recoment> findByInfo(Recoment recoment);

    PageInfo<Recoment> findInfoByPage(Recoment recoment, Integer pageNum, Integer pageSize);
}