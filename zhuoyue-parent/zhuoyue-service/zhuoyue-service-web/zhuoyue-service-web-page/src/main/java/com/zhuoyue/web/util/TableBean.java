package com.zhuoyue.web.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Linmo
 * @create 2020/4/23 20:46
 */
public class TableBean<T> {
    private int index;
    private final int rowSize;
    private List<List<T>> data;

    public TableBean(int rowSize) {
        this.rowSize = rowSize;
        this.data = new ArrayList<>(3);
        data.add(new ArrayList<>());
    }

    public void add(T t) {
        List<T> ts = data.get(index);

        if (ts.size() >= rowSize) {
            index++;
            ts = new ArrayList<>(rowSize);
            data.add(ts);
        }
        ts.add(t);
    }

    public T get(int row, int col) {
        if (row > index)
            throw new IllegalStateException("没有这一行");
        List<T> ts = data.get(row);
        if (col >= ts.size())
            throw new IllegalStateException("没有这一列");
        return ts.get(col);
    }

    public List<List<T>> getData() {
        return data;
    }

    public int getIndex() {
        return index;
    }

    public int getRowSize() {
        return rowSize;
    }
}
