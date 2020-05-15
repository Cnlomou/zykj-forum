package com.zhuoyue.article.service.impl;

import com.zhuoyue.article.dao.ArticleMapper;
import com.zhuoyue.article.dao.CommentMapper;
import com.zhuoyue.article.dao.RecomentMapper;
import com.zhuoyue.article.pojo.Article;
import com.zhuoyue.article.pojo.Recoment;
import com.zhuoyue.article.service.CommentService;
import com.zhuoyue.article.pojo.Comment;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private RecomentMapper recomentMapper;

    @Override
    public void add(Comment comment) {
        Article article = articleMapper.selectByPrimaryKey(comment.getArtId());
        if (article == null)
            return;
        comment.setCreateTime(new Date());
        commentMapper.insertSelective(comment);

        //增加文章的评论数
        Article article1 = new Article();
        article1.setComCount(article.getComCount() + 1);
        article1.setId(article.getId());
        articleMapper.updateByPrimaryKey(article1);
    }

    /**
     * 删除评论，以及回复
     *
     * @param id
     */
    @Override
    public void delById(Long id) {
        //删除回复
        Recoment recoment = new Recoment();
        recoment.setComId(id);
        recomentMapper.delete(recoment);

        //删除评论
        commentMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据文章id查找评论
     *
     * @param id
     * @return
     */
    @Override
    public List<Comment> findByArticle(Long id) {
        Comment comment = new Comment();
        comment.setArtId(id);
        return commentMapper.select(comment);
    }

    /**
     * 根据文章id删除所有的评论及回复
     *
     * @param id
     */
    @Override
    @Transactional
    public void delByArtId(Long id) {

        //删除文章的评论
        Example example = new Example(Comment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("artId", id);
        commentMapper.deleteByExample(example);

        //删除文章评论下的回复
        Example example1 = new Example(Recoment.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("artId", id);
        recomentMapper.deleteByExample(example1);
    }

    @Override
    public void update(Comment comment) {
        commentMapper.updateByPrimaryKeySelective(comment);
    }

    @Override
    public Comment findById(Long id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Comment> findAll() {
        return commentMapper.selectAll();
    }

    @Override
    public PageInfo<Comment> findAllByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(findAll());
    }

    @Override
    public List<Comment> findByInfo(Comment comment) {
        Example example = new Example(Comment.class);
        Example.Criteria criteria = example.createCriteria();
        if (comment.getContent() != null) {
            criteria.andLike("content", "%" + comment.getContent() + "%");
        }
        if (comment.getAccUrl() != null) {
            criteria.andLike("accUrl", "%" + comment.getAccUrl() + "%");
        }
        return commentMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Comment> findInfoByPage(Comment comment, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(findByInfo(comment));
    }
}