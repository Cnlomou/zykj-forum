package com.zhuoyue.web.service.impl;

import com.zhuoyue.article.feign.PlateFeignClient;
import com.zhuoyue.article.pojo.Plate;
import com.zhuoyue.search.feign.ArticleSearchFeignClient;
import com.zhuoyue.search.pojo.ArticleDetail;
import com.zhuoyue.web.bean.EditBean;
import com.zhuoyue.web.service.ViewPageService;
import com.zhuoyue.web.util.StatusUtil;
import com.zhuoyue.web.util.TableBean;
import entity.Page;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Linmo
 * @create 2020/4/23 18:09
 */
@Service
public class ViewPageServiceImpl implements ViewPageService {

    @Autowired
    private PlateFeignClient plateFeignClient;

    @Autowired
    private ArticleSearchFeignClient articleFeignClient;
    private List<Map.Entry<Plate, TableBean<Plate>>> sections = null;
    private Object lock = new Object();

    public void buildSection(Model model) {
        synchronized (lock) {
            if (sections == null) {
                Result<List<Plate>> all = plateFeignClient.findAll();
                StatusUtil.notOk(all.getCode(), "调用article失败");
                List<Plate> data = all.getData();
                Map<Integer, TableBean<Plate>> map = new HashMap<>(4);
                List<Plate> parent = new ArrayList<>();
                for (Plate datum : data) {
                    if (datum.getParId() == 0)
                        parent.add(datum);
                    else {
                        TableBean<Plate> orDefault = map.getOrDefault(datum.getParId(), new TableBean<>(4));
                        orDefault.add(datum);
                        map.put(datum.getParId(), orDefault);
                    }
                }
                //使用list避免顺序打乱，封装到model对象中
                List<Map.Entry<Plate, TableBean<Plate>>> resultSet = new ArrayList<>();
                for (Plate plate : parent) {
                    TableBean<Plate> orDefault = map.getOrDefault(plate.getId(), new TableBean<>(4));
                    resultSet.add(new HashMap.SimpleEntry<>(plate, orDefault));
                }
                this.sections = resultSet;
            }
        }
        model.addAttribute("section", sections);
    }

    @Override
    public void buildForumSectionPage(Model model, Integer id, String par, String son, Integer num, Integer size) {
        //设置用于跳转edit页面的参数
        EditBean editBean = new EditBean();
        editBean.setMode("发帖");
        editBean.setParent(par);
        editBean.setSid(id);
        editBean.setSon(son);
        editBean.setDopost("/user/dosave");//请求后端的url
        model.addAttribute("edit", editBean);

        //设置渲染该板块的参数
        Result<Plate> byId = plateFeignClient.findById(id);
        StatusUtil.notOk(byId.getCode(), "获取板块信息失败");
        model.addAttribute("section", byId.getData());


        //从elasticsearch中搜索
        Page<ArticleDetail> articleByElasticsearch = getArticleByElasticsearch(id, num);
        model.addAttribute("arts", articleByElasticsearch);

    }

    private Page<ArticleDetail> getArticleByElasticsearch(Integer plateId, Integer pageNum) {
        Map<String, String> params = new HashMap<>();
        params.put("pid", plateId.toString());
        params.put("pageNum", String.valueOf(pageNum - 1));
        Result<Map<String, Object>> mapResult = articleFeignClient.searchArticle(params);
        StatusUtil.notOk(mapResult.getCode(), "从elasticsearch中获取article失败");
        Map<String, Object> data1 = mapResult.getData();
        Integer totals = (Integer) data1.get("totals");
        List<ArticleDetail> articleDetails = (List<ArticleDetail>) data1.get("datas");
        Page<ArticleDetail> page = new Page<>(totals, pageNum, articleDetails);
        return page;
    }


}
