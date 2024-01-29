package com.gyunf.entity;

import java.util.List;
import java.util.Map;

/**
 * Created   by  LuChangxian on 2020/5/13
 */
public class GridData {
    private int Total;
    private List<Map<String,Object>> RowsList;
    public int getTotal() {
        return Total;
    }
    public void setTotal(int total) {
        Total = total;
    }
    public List<Map<String, Object>> getRowsList() {
        return RowsList;
    }
    public void setRowsList(List<Map<String, Object>> rowsList) {
        RowsList = rowsList;
    }
}
