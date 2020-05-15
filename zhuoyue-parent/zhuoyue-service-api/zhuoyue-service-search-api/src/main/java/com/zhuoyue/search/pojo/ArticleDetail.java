package com.zhuoyue.search.pojo;

import com.zhuoyue.article.pojo.Comment;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

/**
 * @author Linmo
 * @create 2020/4/19 10:31
 */
@Document(indexName = "article", type = "all")
public class ArticleDetail {
    @Id
    private Long id; /*文章id*/

    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String title; /*文章标题*/

    private String content; /*文章内容*/

    private Integer likeCount; /*喜欢数*/

    @Field(type = FieldType.Keyword)
    private String isTop; /*是否置顶*/

    private String isEss; /*是否精华 默认0*/
    @Field(type = FieldType.Date)
    private Date createTime; /*创建时间*/

    private Integer visCount; /*浏览数*/

    private Integer comCount; /*评论数*/

    @Field(type = FieldType.Keyword)
    private Long userId; /*用户的id*/

    @Field(type = FieldType.Keyword)
    private String plateName; /*板块名*/

    @Field(type = FieldType.Keyword)
    private Integer palteId; /*板块id*/

    private List<Comment> coms;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public String getIsEss() {
        return isEss;
    }

    public void setIsEss(String isEss) {
        this.isEss = isEss;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getVisCount() {
        return visCount;
    }

    public void setVisCount(Integer visCount) {
        this.visCount = visCount;
    }

    public Integer getComCount() {
        return comCount;
    }

    public void setComCount(Integer comCount) {
        this.comCount = comCount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPlateName() {
        return plateName;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    public Integer getPalteId() {
        return palteId;
    }

    public void setPalteId(Integer palteId) {
        this.palteId = palteId;
    }

    public List<Comment> getComs() {
        return coms;
    }

    public void setComs(List<Comment> coms) {
        this.coms = coms;
    }
}
