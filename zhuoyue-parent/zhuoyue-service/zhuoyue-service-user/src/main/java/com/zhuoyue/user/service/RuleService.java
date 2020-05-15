package com.zhuoyue.user.service;

import com.github.pagehelper.PageInfo;

import java.util.List;

import com.zhuoyue.user.pojo.Rule;

public interface RuleService {
    void add(Rule rule);

    void delById(Integer id);

    void update(Rule rule);

    Rule findById(Integer id);

    List<Rule> findAll();

    PageInfo<Rule> findAllByPage(Integer pageNum, Integer pageSize);

    List<Rule> findByInfo(Rule rule);

    PageInfo<Rule> findInfoByPage(Rule rule, Integer pageNum, Integer pageSize);
}