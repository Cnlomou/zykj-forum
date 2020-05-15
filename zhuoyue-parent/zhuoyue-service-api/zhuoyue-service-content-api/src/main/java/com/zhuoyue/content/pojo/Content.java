package com.zhuoyue.content.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_content")
public class Content {
    @Id
    private Integer id; /*id*/

    @Column(name = "create_time")
    private Date createTime; /*创建时间*/

    @Column(name = "up_time")
    private Date upTime; /*上架时间*/

    @Column(name = "down_time")
    private Date downTime; /*下架时间*/

    @Column(name = "status")
    private String status; /*0 下架 1上架 默认0*/

    @Column(name = "title")
    private String title; /*广告头*/

    @Column(name = "loc_url")
    private String locUrl; /*跳转url*/

    @Column(name = "cate_id")
    private Integer cateId; /*类型id*/

    public Content() {
    }

    public void setDownTime(Date downTime) {
        this.downTime = downTime;
    }

    public Date getDownTime() {
        return this.downTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public Date getUpTime() {
        return this.upTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public Integer getCateId() {
        return this.cateId;
    }

    public void setLocUrl(String locUrl) {
        this.locUrl = locUrl;
    }

    public String getLocUrl() {
        return this.locUrl;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public String toString() {
        return "Content{" +
                "downTime=" + downTime +
                "upTime=" + upTime +
                "createTime=" + createTime +
                "cateId=" + cateId +
                "locUrl=" + locUrl +
                "id=" + id +
                "title=" + title +
                "status=" + status +
                '}';
    }
}