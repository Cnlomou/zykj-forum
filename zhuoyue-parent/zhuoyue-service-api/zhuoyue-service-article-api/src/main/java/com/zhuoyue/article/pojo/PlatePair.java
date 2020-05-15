package com.zhuoyue.article.pojo;

/**
 * @author Linmo
 * @create 2020/4/25 20:23
 */
public class PlatePair {
    private Plate parent;
    private Plate content;

    public Plate getParent() {
        return parent;
    }

    public void setParent(Plate parent) {
        this.parent = parent;
    }

    public Plate getContent() {
        return content;
    }

    public void setContent(Plate content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PlatePair{" +
                "parent=" + parent +
                ", content=" + content +
                '}';
    }
}
