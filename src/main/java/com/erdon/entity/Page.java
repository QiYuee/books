package com.erdon.entity;

import java.util.List;

public class  Page<T> {
    private final static  Integer PAGE_SIZE = 4;
    private Integer pageNo;
    private Integer pageTotal;
    private Integer pageCount;
    private List<T> items;
    private Integer pageSize = PAGE_SIZE;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Page() {
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageCount=" + pageCount +
                ", items=" + items +
                ", pageSize=" + pageSize +
                ", url='" + url + '\'' +
                '}';
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
