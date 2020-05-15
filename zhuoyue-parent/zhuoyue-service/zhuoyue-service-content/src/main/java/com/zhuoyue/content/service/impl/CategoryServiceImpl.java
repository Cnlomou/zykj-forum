package com.zhuoyue.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuoyue.content.dao.CategoryMapper;
import com.zhuoyue.content.pojo.Category;
import com.zhuoyue.content.service.CategoryService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;


    @Override
    public void add(Category category) {
        categoryMapper.insertSelective(category);
    }

    @Override
    public void delById(Integer id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryMapper.selectAll();
    }

    @Override
    public PageInfo<Category> findAllByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(findAll());
    }

    @Override
    public List<Category> findByInfo(Category category) {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        if (category.getDes() != null) {
            criteria.andLike("des", "%" + category.getDes() + "%");
        }
        return categoryMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Category> findInfoByPage(Category category, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(findByInfo(category));
    }
}