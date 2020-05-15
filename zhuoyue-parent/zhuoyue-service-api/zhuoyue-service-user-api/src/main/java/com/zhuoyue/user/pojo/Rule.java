package com.zhuoyue.user.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_rule")
public class Rule {
    @Id
    private Integer id; /*id*/

    @Column(name = "name")
    private String name; /*角色名*/

    @Column(name = "des")
    private String des; /*角色描述*/

    @Column(name = "scop")
    private String scop; /*0 子板块 1 父版块 2 全局*/

    public Rule() {
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDes() {
        return this.des;
    }

    public void setScop(String scop) {
        this.scop = scop;
    }

    public String getScop() {
        return this.scop;
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

    public String toString() {
        return "Rule{" +
                "des=" + des +
                "scop=" + scop +
                "name=" + name +
                "id=" + id +
                '}';
    }
}