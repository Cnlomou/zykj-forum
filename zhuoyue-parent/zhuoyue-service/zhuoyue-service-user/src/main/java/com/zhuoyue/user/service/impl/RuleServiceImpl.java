package com.zhuoyue.user.service.impl;

import com.zhuoyue.user.dao.RuleMapper;
import com.zhuoyue.user.dao.UserMapper;
import com.zhuoyue.user.pojo.User;
import com.zhuoyue.user.service.RuleService;
import com.zhuoyue.user.pojo.Rule;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RuleServiceImpl implements RuleService {
    @Resource
    private RuleMapper ruleMapper;


    @Resource
    private UserMapper userMapper;

    @Override
    public void add(Rule rule) {

        ruleMapper.insertSelective(rule);
    }

    /**
     * 删除一种角色并删除他的所有用户
     *
     * @param id
     */
    @Override
    @Transactional
    public void delById(Integer id) {
        User user = new User();
        user.setRuleId(id);
        userMapper.delete(user);
        ruleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Rule rule) {
        ruleMapper.updateByPrimaryKeySelective(rule);
    }

    @Override
    public Rule findById(Integer id) {
        return ruleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Rule> findAll() {
        return ruleMapper.selectAll();
    }

    @Override
    public PageInfo<Rule> findAllByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(findAll());
    }

    @Override
    public List<Rule> findByInfo(Rule rule) {
        Example example = new Example(Rule.class);
        Example.Criteria criteria = example.createCriteria();
        if (rule.getName() != null) {
            criteria.andLike("name", "%" + rule.getName() + "%");
        }
        if (rule.getDes() != null) {
            criteria.andLike("des", "%" + rule.getDes() + "%");
        }
        if (rule.getScop() != null) {
            criteria.andLike("scop", "%" + rule.getScop() + "%");
        }
        return ruleMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Rule> findInfoByPage(Rule rule, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(findByInfo(rule));
    }
}