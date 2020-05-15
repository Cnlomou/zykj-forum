package entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class Message implements Serializable {
    private int code;

    private Object content;

    @JSONField(serialize = false)
    private String routeKey;

    @JSONField(serialize = false)
    private String exechange;

    public Message() {
    }

    public Message(int code, Object content) {
        this.code = code;
        this.content = content;
    }

    public Message(int code, Object content, String routeKey, String exechange) {
        this.code = code;
        this.content = content;
        this.routeKey = routeKey;
        this.exechange = exechange;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey;
    }

    public String getExechange() {
        return exechange;
    }

    public void setExechange(String exechange) {
        this.exechange = exechange;
    }

    @Override
    public String toString() {
        return "Message{" +
                "code=" + code +
                ", content=" + content +
                ", routeKey='" + routeKey + '\'' +
                ", exechange='" + exechange + '\'' +
                '}';
    }
}
