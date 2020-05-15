package com.zhuoyue.article.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_comment")
public class Comment {
    @Id
    private Long id; /*评论id*/

    @Column(name = "user_id")
    private Long userId; /*用户id*/

    @Column(name = "art_id")
    private Long artId; /*文章id*/

    @Column(name = "like_count")
    private Integer likeCount; /*点赞数 默认0*/

    @Column(name = "content")
    private String content; /*发布的文本*/

    @Column(name = "acc_url")
    private String accUrl; /*附件，不超过5个*/

    @Column(name = "par_id")
    private Long parId; /*上一级id 默认0*/

    @Column(name = "create_time")
    private Date createTime; /*创建时间*/

    @Column(name = "com_count")
    private Integer comCount; /*拥有子评论的数量 默认0*/

    public Comment() {
    }

    public void setArtId(Long artId) {
        this.artId = artId;
    }

    public Long getArtId() {
        return this.artId;
    }

    public void setComCount(Integer comCount) {
        this.comCount = comCount;
    }

    public Integer getComCount() {
        return this.comCount;
    }

    public void setParId(Long parId) {
        this.parId = parId;
    }

    public Long getParId() {
        return this.parId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setAccUrl(String accUrl) {
        this.accUrl = accUrl;
    }

    public String getAccUrl() {
        return this.accUrl;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getLikeCount() {
        return this.likeCount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
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

    public String toString() {
        return "Comment{" +
                "artId=" + artId +
                "comCount=" + comCount +
                "parId=" + parId +
                "createTime=" + createTime +
                "accUrl=" + accUrl +
                "likeCount=" + likeCount +
                "id=" + id +
                "userId=" + userId +
                "content=" + content +
                '}';
    }
}