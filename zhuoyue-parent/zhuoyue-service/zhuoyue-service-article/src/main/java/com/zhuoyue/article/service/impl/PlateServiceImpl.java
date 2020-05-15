package com.zhuoyue.article.service.impl;

import com.zhuoyue.article.dao.PlateMapper;
import com.zhuoyue.article.pojo.PlatePair;
import com.zhuoyue.article.service.PlateService;
import com.zhuoyue.article.pojo.Plate;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.jsonwebtoken.lang.Assert;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlateServiceImpl implements PlateService {
    @Resource
    private PlateMapper plateMapper;


    @Override
    public boolean incrementArticleCount(Integer id) {
        Plate plate = new Plate();
        Plate plate1 = plateMapper.selectByPrimaryKey(id);
        if (plate1 != null) {
            plate.setId(id);
            plate.setConCount(plate1.getConCount() + 1);
            return plateMapper.updateByPrimaryKeySelective(plate) > 0;
        }
        return false;
    }

    @Override
    public void add(Plate plate) {
        plateMapper.insertSelective(plate);
    }

    @Override
    public List<PlatePair> getRecommendPlate(Integer size) {
        PageHelper.startPage(0, size);
        //查询出推荐的板块
        Example example = new Example(Plate.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("parId", 0);
        example.orderBy("sort").desc();
        List<Plate> plates = plateMapper.selectByExample(example);

        //构建plaePair
        List<PlatePair> pairs = new ArrayList<>(plates.size());
        Map<Integer, Plate> map = new HashMap<>(4);
        for (Plate plate : plates) {
            Plate par = null;
            Integer parId = plate.getParId();
            if (!map.containsKey(parId)) {
                par = findById(parId);
                Assert.notNull(par, plate.toString() + "没有对应的父板块");
                map.put(parId, par);
            }
            PlatePair platePair = new PlatePair();
            platePair.setParent(map.get(plate.getParId()));
            platePair.setContent(plate);
            pairs.add(platePair);
        }
        return pairs;
    }

    @Override
    public void delById(Integer id) {
        plateMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Plate plate) {
        plateMapper.updateByPrimaryKeySelective(plate);
    }

    @Override
    public Plate findById(Integer id) {
        return plateMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Plate> findAll() {
        return plateMapper.selectAll();
    }

    @Override
    public PageInfo<Plate> findAllByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(findAll());
    }

    @Override
    public List<Plate> findByInfo(Plate plate) {
        Example example = new Example(Plate.class);
        Example.Criteria criteria = example.createCriteria();
        if (plate.getName() != null) {
            criteria.andLike("name", "%" + plate.getName() + "%");
        }
        if (plate.getPicUrl() != null) {
            criteria.andLike("picUrl", "%" + plate.getPicUrl() + "%");
        }
        if (plate.getStatus() != null) {
            criteria.andLike("status", "%" + plate.getStatus() + "%");
        }
        if (plate.getParId() != null) {
            criteria.andEqualTo("parId", plate.getParId());
        }
        return plateMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Plate> findInfoByPage(Plate plate, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(findByInfo(plate));
    }
}