package com.zhuoyue.article.service.impl;

import com.zhuoyue.article.dao.RecomentMapper;
import com.zhuoyue.article.service.RecomentService;
import com.zhuoyue.article.pojo.Recoment;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class RecomentServiceImpl implements RecomentService {
    @Resource
    private RecomentMapper recomentMapper;


    @Override
    public void add(Recoment recoment) {
        recoment.setCreateTime(new Date());
        recomentMapper.insertSelective(recoment);
    }

    @Override
    public void delById(Long id) {
        recomentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Recoment recoment) {
        recomentMapper.updateByPrimaryKeySelective(recoment);
    }

    @Override
    public Recoment findById(Long id) {
        return recomentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Recoment> findAll() {
        return recomentMapper.selectAll();
    }

    @Override
    public PageInfo<Recoment> findAllByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(findAll());
    }

    @Override
    public List<Recoment> findByInfo(Recoment recoment) {
        Example example = new Example(Recoment.class);
        Example.Criteria criteria = example.createCriteria();
        if (recoment.getContent() != null) {
            criteria.andLike("content", "%" + recoment.getContent() + "%");
        }
        if (recoment.getAccUrl() != null) {
            criteria.andLike("accUrl", "%" + recoment.getAccUrl() + "%");
        }
        return recomentMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Recoment> findInfoByPage(Recoment recoment, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(findByInfo(recoment));
    }
}