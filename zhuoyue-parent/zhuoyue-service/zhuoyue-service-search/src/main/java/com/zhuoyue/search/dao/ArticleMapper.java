package com.zhuoyue.search.dao;


import com.zhuoyue.search.pojo.ArticleDetail;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Linmo
 * @create 2020/4/19 10:55
 */

public interface ArticleMapper extends ElasticsearchRepository<ArticleDetail, Long> {
}
