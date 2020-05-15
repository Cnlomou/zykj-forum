package com.zhuoyue.content.service;

import com.github.pagehelper.PageInfo;
import com.zhuoyue.content.pojo.Content;

import java.util.List;

public interface ContentService {
    void add(Content content);

    void delById(Integer id);

    void update(Content content);

    Content findById(Integer id);

    List<Content> findAll();

    PageInfo<Content> findAllByPage(Integer pageNum, Integer pageSize);

    List<Content> findByInfo(Content content);

    PageInfo<Content> findInfoByPage(Content content, Integer pageNum, Integer pageSize);
}