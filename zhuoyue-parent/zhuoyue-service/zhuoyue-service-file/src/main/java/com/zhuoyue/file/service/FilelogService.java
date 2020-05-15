package com.zhuoyue.file.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zhuoyue.file.pojo.Filelog;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

public interface FilelogService {

    PageInfo<Filelog> getByTime(Date bg, Date lg, int num, int size);

    void add(Filelog filelog);

    void delById(Long id);

    void update(Filelog filelog);

    Filelog findById(Long id);

    List<Filelog> findAll();

    PageInfo<Filelog> findAllByPage(Integer pageNum, Integer pageSize);

    List<Filelog> findByInfo(Filelog filelog);

    PageInfo<Filelog> findInfoByPage(Filelog filelog, Integer pageNum, Integer pageSize);

    void log(HttpServletRequest httpRequest, String file, String method, String url);
}