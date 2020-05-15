package com.zhuoyue.article.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_plate")
public class Plate {
    @Id
    private Integer id; /*板块id*/

    @Column(name = "name")
    private String name; /*板块名*/

    @Column(name = "con_count")
    private Integer conCount; /*内容数*/

    @Column(name = "par_id")
    private Integer parId; /*上一级id 没有上一级为0*/

    @Column(name = "pic_url")
    private String picUrl; /*图片url*/

    @Column(name = "status")
    private String status; /*状态 0维护,不能发帖 1正常 默认为1*/

    @Column(name = "sort")
    private Integer sort;  /*用于排序*/

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Plate() {
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicUrl() {
        return this.picUrl;
    }

    public void setParId(Integer parId) {
        this.parId = parId;
    }

    public Integer getParId() {
        return this.parId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setConCount(Integer conCount) {
        this.conCount = conCount;
    }

    public Integer getConCount() {
        return this.conCount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public String toString() {
        return "Plate{" +
                "picUrl=" + picUrl +
                "parId=" + parId +
                "name=" + name +
                "id=" + id +
                "conCount=" + conCount +
                "status=" + status +
                '}';
    }
}