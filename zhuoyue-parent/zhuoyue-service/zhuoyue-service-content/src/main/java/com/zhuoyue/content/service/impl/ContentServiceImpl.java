package com.zhuoyue.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuoyue.content.dao.ContentMapper;
import com.zhuoyue.content.pojo.Content;
import com.zhuoyue.content.service.ContentService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Resource
    private ContentMapper contentMapper;


    @Override
    public void add(Content content) {
        content.setCreateTime(new Date());
        contentMapper.insertSelective(content);
    }

    @Override
    public void delById(Integer id) {
        contentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Content content) {
        contentMapper.updateByPrimaryKeySelective(content);
    }

    @Override
    public Content findById(Integer id) {
        return contentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Content> findAll() {
        return contentMapper.selectAll();
    }

    @Override
    public PageInfo<Content> findAllByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(findAll());
    }

    @Override
    public List<Content> findByInfo(Content content) {
        Example example = new Example(Content.class);
        Example.Criteria criteria = example.createCriteria();
        if (content.getStatus() != null) {
            criteria.andLike("status", "%" + content.getStatus() + "%");
        }
        if (content.getTitle() != null) {
            criteria.andLike("title", "%" + content.getTitle() + "%");
        }
        if (content.getLocUrl() != null) {
            criteria.andLike("locUrl", "%" + content.getLocUrl() + "%");
        }
        return contentMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Content> findInfoByPage(Content content, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(findByInfo(content));
    }
}