package com.zhuoyue.content.service;

import com.github.pagehelper.PageInfo;
import com.zhuoyue.content.pojo.Category;

import java.util.List;

public interface CategoryService {
    void add(Category category);

    void delById(Integer id);

    void update(Category category);

    Category findById(Integer id);

    List<Category> findAll();

    PageInfo<Category> findAllByPage(Integer pageNum, Integer pageSize);

    List<Category> findByInfo(Category category);

    PageInfo<Category> findInfoByPage(Category category, Integer pageNum, Integer pageSize);
}