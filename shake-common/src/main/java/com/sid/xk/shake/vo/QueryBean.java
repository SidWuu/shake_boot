package com.sid.xk.shake.vo;

import java.io.Serializable;

/**
 * 公共查询条件
 * @author wuxiaodong
 * @date 2021/04/05
 */
public class QueryBean implements Serializable {

    private static final long serialVersionUID = 1L;
    /** 总行数 */
    private long total;
    /** 每页行数 */
    private long size;
    /** 当前页 */
    private long current;
    /** 查询开始时间 */
    private String startDate;
    /** 查询结束时间 */
    private String endDate;
    /** 模糊查询字段 */
    private String[] likeCols;
    /** 排序字段 */
    private String[] orderCols;
    /** 分组字段 */
    private String[] groupCols;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String[] getLikeCols() {
        return likeCols;
    }

    public void setLikeCols(String[] likeCols) {
        this.likeCols = likeCols;
    }

    public String[] getOrderCols() {
        return orderCols;
    }

    public void setOrderCols(String[] orderCols) {
        this.orderCols = orderCols;
    }

    public String[] getGroupCols() {
        return groupCols;
    }

    public void setGroupCols(String[] groupCols) {
        this.groupCols = groupCols;
    }
}
