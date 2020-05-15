package com.zhuoyue.search.service;

import com.zhuoyue.search.pojo.ArticleDetail;

import java.util.Map;

/**
 * @author Linmo
 * @create 2020/4/19 10:58
 */


public interface ArticleService {

    Map<String, Object> searchArticle(Map<String, String> params);

    Map<String, ?> keyWordSearch(Map<String, String> parms);

    String importData(int pageNum, int pageSize);

    String importDateAll();
}
