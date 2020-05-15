package com.zhuoyue.article.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_recoment")
public class Recoment {
    @Id
    private Long id; /*id*/

    @Column(name = "com_id")
    private Long comId; /*所处的评论下*/

    @Column(name = "recom_id")
    private Long recomId; /*回复的id  有表示回复回复，无表示回复评论*/

    @Column(name = "art_id")
    private Long artId; /*文章id*/

    @Column(name = "user_id")
    private Long userId; /*用户id*/

    @Column(name = "content")
    private String content; /*文字内容*/

    @Column(name = "acc_url")
    private String accUrl; /*附件 最多5个*/

    @Column(name = "create_time")
    private Date createTime; /*创建时间*/

    public Recoment() {
    }

    public void setArtId(Long artId) {
        this.artId = artId;
    }

    public Long getArtId() {
        return this.artId;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setComId(Long comId) {
        this.comId = comId;
    }

    public Long getComId() {
        return this.comId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setRecomId(Long recomId) {
        this.recomId = recomId;
    }

    public Long getRecomId() {
        return this.recomId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public String toString() {
        return "Recoment{" +
                "artId=" + artId +
                "createTime=" + createTime +
                "accUrl=" + accUrl +
                "id=" + id +
                "comId=" + comId +
                "userId=" + userId +
                "recomId=" + recomId +
                "content=" + content +
                '}';
    }
}