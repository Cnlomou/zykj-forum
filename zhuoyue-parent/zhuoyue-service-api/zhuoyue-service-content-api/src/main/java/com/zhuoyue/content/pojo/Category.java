package com.zhuoyue.content.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_category")
public class Category {
    @Id
    private Integer id; /*id*/

    @Column(name = "count")
    private Integer count; /*广告数 默认0*/

    @Column(name = "des")
    private String des; /*描述*/

    public Category() {
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDes() {
        return this.des;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public String toString() {
        return "Category{" +
                "des=" + des +
                "count=" + count +
                "id=" + id +
                '}';
    }
}