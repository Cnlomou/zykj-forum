package com.zhuoyue.web.bean;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Linmo
 * @create 2020/4/26 15:27
 */
public class EditBean {
    //模式发帖，编辑
    @NotBlank
    private String mode;
    //父板块名
    @NotBlank
    private String parent;
    //子板块id
    @NotNull
    @NumberFormat
    private Integer sid;
    //子板块名
    @NotBlank
    private String son;
    //后台请求url
    @NotBlank
    private String dopost;

    @Override
    public String toString() {
        return "EditBean{" +
                "mode='" + mode + '\'' +
                ", parent='" + parent + '\'' +
                ", sid=" + sid +
                ", son='" + son + '\'' +
                ", dopost='" + dopost + '\'' +
                '}';
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSon() {
        return son;
    }

    public void setSon(String son) {
        this.son = son;
    }

    public String getDopost() {
        return dopost;
    }

    public void setDopost(String dopost) {
        this.dopost = dopost;
    }
}
