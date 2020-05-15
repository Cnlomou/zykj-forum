package com.zhuoyue.article.service.impl;

import com.zhuoyue.article.dao.ArticleMapper;
import com.zhuoyue.article.service.ArticleService;
import com.zhuoyue.article.pojo.Article;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuoyue.article.service.CommentService;
import com.zhuoyue.article.service.PlateService;
import com.zhuoyue.user.feign.UserFeignClient;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private PlateService plateService;

    @Override
    public PageInfo<Article> findByPlateId(Integer id, Integer num, Integer size) {
        Article article = new Article();
        article.setPalteId(id);
        PageHelper.startPage(num, size);
        List<Article> select = articleMapper.select(article);
        return new PageInfo<>(select);
    }

    /**
     * 增加访问量
     *
     * @param id
     */
    @Override
    public void addVisit(Long id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        if (article == null)
            return;
        Article article1 = new Article();
        article1.setId(article.getId());
        article1.setVisCount(article.getVisCount() + 1);
        articleMapper.updateByPrimaryKey(article1);
    }

    @Override
    @Transactional
    public void add(Article article) {
        article.setCreateTime(new Date());
        articleMapper.insertSelective(article);
        Result result = userFeignClient.updateArticleCount(article.getUserId());
        if (!result.isFlag() || !plateService.incrementArticleCount(article.getPalteId()))
            throw new IllegalStateException("更新用户的文章数失败");
    }

    @Override
    @Transactional
    public void delById(Long id) {
        commentService.delByArtId(id);    //删除评论及回复
        articleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 以修改标志位的方式来删除
     *
     * @param id
     */
    @Override
    public void delByStatus(Long id) {
        Article article = new Article();
        article.setId(id);
        article.setStatus("0");
        articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    @Transactional
    public void update(Article article) {
        articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public Article findById(Long id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Article> findAll() {
        return articleMapper.selectAll();
    }

    @Override
    public PageInfo<Article> findAllByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(findAll());
    }

    @Override
    public List<Article> findByInfo(Article article) {
        Example example = new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();
        if (article.getTitle() != null) {
            criteria.andLike("title", "%" + article.getTitle() + "%");
        }
        if (article.getContent() != null) {
            criteria.andLike("content", "%" + article.getContent() + "%");
        }
        if (article.getIsTop() != null) {
            criteria.andLike("isTop", "%" + article.getIsTop() + "%");
        }
        if (article.getStatus() != null) {
            criteria.andLike("status", "%" + article.getStatus() + "%");
        }
        if (article.getIsEss() != null) {
            criteria.andLike("isEss", "%" + article.getIsEss() + "%");
        }
        if (article.getPlateName() != null) {
            criteria.andLike("plateName", "%" + article.getPlateName() + "%");
        }
        return articleMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Article> findInfoByPage(Article article, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(findByInfo(article));
    }
}