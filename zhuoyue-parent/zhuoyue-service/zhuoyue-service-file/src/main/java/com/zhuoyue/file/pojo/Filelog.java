package com.zhuoyue.file.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_filelog")
public class Filelog {
    @Id
    private Long id; /*主键*/

    @Column(name = "ip")
    private String ip; /*ip*/

    @Column(name = "file_name")
    private String fileName; /*文件名*/

    @Column(name = "url")
    private String url; /*保存至文件服务器的url*/

    @Column(name = "time")
    private Date time; /*时间*/

    @Column(name = "method")
    private String method; /*操作方式*/

    public Filelog() {
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return this.method;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return this.ip;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return this.time;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public String toString() {
        return "Filelog{" +
                "fileName=" + fileName +
                "method=" + method +
                "ip=" + ip +
                "id=" + id +
                "time=" + time +
                "url=" + url +
                '}';
    }
}