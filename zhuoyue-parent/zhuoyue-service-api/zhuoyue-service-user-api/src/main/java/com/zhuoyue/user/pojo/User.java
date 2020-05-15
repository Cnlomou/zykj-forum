package com.zhuoyue.user.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_user")
public class User {
    @Id
    private Long id; /*id*/

    @Column(name = "rule_id")
    private Integer ruleId; /*角色id*/

    @Column(name = "name")
    private String name; /*昵称*/

    @Column(name = "create_time")
    private Date createTime; /*创建时间*/

    @Column(name = "email")
    private String email; /*邮件*/

    @Column(name = "phone")
    private String phone; /*电话*/

    @Column(name = "status")
    private String status; /*默认1正常 0冻结,只能看*/

    @Column(name = "username")
    private String username; /*登陆账号*/

    @Column(name = "password")
    private String password; /*登陆密码*/

    @Column(name = "art_count")
    private Integer artCount; /*帖数 默认0*/

    @Column(name = "share_count")
    private Integer shareCount; /*分享数 默认0*/

    @Column(name = "pic_url")
    private String picUrl; /*图片url*/

    public User() {
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public Integer getShareCount() {
        return this.shareCount;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicUrl() {
        return this.picUrl;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setArtCount(Integer artCount) {
        this.artCount = artCount;
    }

    public Integer getArtCount() {
        return this.artCount;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getRuleId() {
        return this.ruleId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public String toString() {
        return "User{" +
                "shareCount=" + shareCount +
                "picUrl=" + picUrl +
                "password=" + password +
                "artCount=" + artCount +
                "createTime=" + createTime +
                "phone=" + phone +
                "name=" + name +
                "id=" + id +
                "ruleId=" + ruleId +
                "email=" + email +
                "status=" + status +
                "username=" + username +
                '}';
    }
}