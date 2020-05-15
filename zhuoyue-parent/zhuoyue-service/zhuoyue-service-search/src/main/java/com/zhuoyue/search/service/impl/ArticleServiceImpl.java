package com.zhuoyue.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zhuoyue.article.feign.ArticleFeignClient;
import com.zhuoyue.article.feign.CommentFeignClient;
import com.zhuoyue.article.pojo.Article;
import com.zhuoyue.article.pojo.Comment;
import com.zhuoyue.search.dao.ArticleMapper;
import com.zhuoyue.search.pojo.ArticleDetail;
import com.zhuoyue.search.service.ArticleService;
import com.zhuoyue.search.util.StatusCheck;
import entity.Result;
import org.apache.http.util.Asserts;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Linmo
 * @create 2020/4/19 10:58
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleFeignClient articleFeignClient;
    @Autowired
    private CommentFeignClient commentFeignClient;

    @Autowired
    private ArticleMapper articleMapper;


    /**
     * 搜索
     *
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> searchArticle(Map<String, String> params) {
        if (params == null || params.size() == 0)
            throw new IllegalStateException("不能没有参数");


        String plateId = params.get("pid");  //板块id
        Asserts.notEmpty(plateId, "plateId");
        String stype = params.get("sfield"); //排序字段

        //获取分页参数
        Integer pageNum = getPageParam(params);

        //默认方式
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.queryStringQuery(plateId).field("palteId"));
        nativeSearchQueryBuilder.withPageable(PageRequest.of(pageNum, 30));

        //构建排序
        buildSortQuery(stype, nativeSearchQueryBuilder);


        Page<ArticleDetail> search = articleMapper.search(nativeSearchQueryBuilder.build());

        //构建返回map

        return buildResultMap(search);
    }

    private Integer getPageParam(Map<String, String> params) {
        String strNum = params.get("pageNum");
        Integer pageNum = 0;
        try {
            if (!StringUtils.isEmpty(strNum))
                pageNum = Integer.valueOf(strNum);
        } catch (Exception ignored) {
        }
        return pageNum;
    }

    private Map<String, Object> buildResultMap(Page<ArticleDetail> search) {
        Long totals = search.getTotalElements();
        List<ArticleDetail> content = search.getContent();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("totals", totals);
        resultMap.put("datas", content);
        return resultMap;
    }

    private void buildSortQuery(String stype, NativeSearchQueryBuilder nativeSearchQueryBuilder) {
        nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("isTop").order(SortOrder.DESC));
        if (!StringUtils.isEmpty(stype)) {
            switch (stype) {
                case "time":
                    FieldSortBuilder createTime = SortBuilders.fieldSort("createTime").order(SortOrder.DESC);
                    nativeSearchQueryBuilder.withSort(createTime);
                    break;
                case "com":
                    FieldSortBuilder comCount = SortBuilders.fieldSort("comCount").order(SortOrder.DESC);
                    nativeSearchQueryBuilder.withSort(comCount);
                    break;
                case "visit":
                    FieldSortBuilder visCount = SortBuilders.fieldSort("visCount").order(SortOrder.DESC);
                    nativeSearchQueryBuilder.withSort(visCount);
                    break;
                case "ess":
                    FieldSortBuilder isEss = SortBuilders.fieldSort("isEss").order(SortOrder.DESC);
                    nativeSearchQueryBuilder.withSort(isEss);
                    break;
            }
        }
    }

    @Override
    public Map<String, ?> keyWordSearch(Map<String, String> parms) {
        if (parms == null || parms.size() == 0)
            throw new IllegalStateException("不能没有请求参数");
        String stype = parms.get("sfield");

        Integer pageParam = getPageParam(parms);

        String keyword = parms.get("keyword");  //关键字字段
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        if (!StringUtils.isEmpty(keyword)) {
            BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
            boolQueryBuilder.must(QueryBuilders.queryStringQuery(keyword).field("title"));
            nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        }

        nativeSearchQueryBuilder.withPageable(PageRequest.of(pageParam, 30));

        buildSortQuery(stype, nativeSearchQueryBuilder);

        Page<ArticleDetail> search = articleMapper.search(nativeSearchQueryBuilder.build());
        return buildResultMap(search);
    }

    /**
     * @param pageNum  从1开始
     * @param pageSize
     * @return
     */
    @Override
    public String importData(int pageNum, int pageSize) {
        Result<PageInfo<Article>> allByPage = articleFeignClient.findAllByPage(pageNum, pageSize);
        StatusCheck.checkOk(allByPage.getCode(), "向es导入数据失败");

        //构建文章信息
        List<ArticleDetail> articleDetails = buildArticleDetails(allByPage);

        //存入es
        articleMapper.saveAll(articleDetails);
        return String.format("输入%s页成功,pages:%s,pageSize:%s", pageNum, allByPage.getData().getPages(), pageSize);
    }

    @Override
    public String importDateAll() {
        Result<PageInfo<Article>> allByPage = articleFeignClient.findAllByPage(1, 20);
        StatusCheck.checkOk(allByPage.getCode(), "向es导入数据失败");
        //构建articleDetail
        List<ArticleDetail> articleDetails = buildArticleDetails(allByPage);
        articleMapper.saveAll(articleDetails);

        //导入其余的所有文章
        int pages = allByPage.getData().getPages();
        for (int i = 2; i <= pages; i++) {
            allByPage = articleFeignClient.findAllByPage(i, 20);
            articleDetails = buildArticleDetails(allByPage);
            articleMapper.saveAll(articleDetails);
        }


        return "已导入所有文章信息";
    }

    private List<ArticleDetail> buildArticleDetails(Result<PageInfo<Article>> allByPage) {
        PageInfo<Article> data = allByPage.getData();
        String content = JSON.toJSONString(data.getList());
        List<ArticleDetail> articleDetails = JSON.parseArray(content, ArticleDetail.class);

        //将comment设置进去
        for (ArticleDetail articleDetail : articleDetails) {
            Long id = articleDetail.getId();
            Result<List<Comment>> byArtId = commentFeignClient.findByArtId(id);
            StatusCheck.checkOk(allByPage.getCode(), "向es导入数据失败");
            articleDetail.setComs(byArtId.getData());
        }

        return articleDetails;
    }


}
