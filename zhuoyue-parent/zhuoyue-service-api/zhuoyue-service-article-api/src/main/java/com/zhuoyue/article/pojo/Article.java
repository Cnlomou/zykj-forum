package com.zhuoyue.article.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_article")
@ApiModel
public class Article {
    @Id
    @ApiModelProperty(notes = "文章id")
    private Long id; /*文章id*/

    @Column(name = "title")
    @ApiModelProperty(notes = "文章标题", required = true)
    private String title; /*文章标题*/

    @Column(name = "content")
    @ApiModelProperty(notes = "文章内容", required = true)
    private String content; /*文章内容*/

    @Column(name = "like_count")
    @ApiModelProperty(notes = "喜欢数")
    private Integer likeCount; /*喜欢数*/

    @Column(name = "is_top")
    @ApiModelProperty(notes = "是否置顶")
    private String isTop; /*是否置顶*/

    @Column(name = "status")
    @ApiModelProperty(notes = "0删除 1发布 默认1")
    private String status; /*0删除 1发布 默认1*/

    @Column(name = "is_ess")
    @ApiModelProperty(notes = "是否精华 默认0")
    private String isEss; /*是否精华 默认0*/

    @Column(name = "create_time")
    @ApiModelProperty(notes = "创建时间")
    private Date createTime; /*创建时间*/

    @Column(name = "vis_count")
    @ApiModelProperty(notes = "浏览数")
    private Integer visCount; /*浏览数*/

    @Column(name = "com_count")
    @ApiModelProperty(notes = "评论数")
    private Integer comCount; /*评论数*/

    @Column(name = "user_id")
    @ApiModelProperty(notes = "用户的id", required = true)
    private Long userId; /*用户的id*/

    @Column(name = "plate_name")
    @ApiModelProperty(notes = "板块名", required = true)
    private String plateName; /*板块名*/

    @Column(name = "palte_id")
    @ApiModelProperty(notes = "板块id", required = true)
    private Integer palteId; /*板块id*/

    @Column(name = "sort")
    @ApiModelProperty(notes = "用于排序")
    private Integer sort; /*用于排序*/

    public Article() {
    }

    public void setComCount(Integer comCount) {
        this.comCount = comCount;
    }

    public Integer getComCount() {
        return this.comCount;
    }

    public void setIsEss(String isEss) {
        this.isEss = isEss;
    }

    public String getIsEss() {
        return this.isEss;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getLikeCount() {
        return this.likeCount;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setPalteId(Integer palteId) {
        this.palteId = palteId;
    }

    public Integer getPalteId() {
        return this.palteId;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    public String getPlateName() {
        return this.plateName;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setVisCount(Integer visCount) {
        this.visCount = visCount;
    }

    public Integer getVisCount() {
        return this.visCount;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public String getIsTop() {
        return this.isTop;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public String toString() {
        return "Article{" +
                "comCount=" + comCount +
                "isEss=" + isEss +
                "likeCount=" + likeCount +
                "sort=" + sort +
                "title=" + title +
                "userId=" + userId +
                "content=" + content +
                "palteId=" + palteId +
                "plateName=" + plateName +
                "createTime=" + createTime +
                "visCount=" + visCount +
                "isTop=" + isTop +
                "id=" + id +
                "status=" + status +
                '}';
    }
}