package com.srit.ecs.phone.util;

import org.springframework.data.domain.Sort;

import java.io.Serializable;

public class PageDetails implements Serializable {
    private Integer pageNumber;
    private Integer pageSize;
    private String sortName;
    private Sort.Direction sortOrder;

    @Override
    public String toString() {
        return super.toString();
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public Sort.Direction getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Sort.Direction sortOrder) {
        this.sortOrder = sortOrder;
    }
}
