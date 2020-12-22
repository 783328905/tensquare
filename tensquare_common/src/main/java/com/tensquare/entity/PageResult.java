package com.tensquare.entity;

import lombok.Data;

import java.util.List;

/**
 * 2 * @Author: Cai
 * 3 * @Date: 2020/7/21 11:01
 * 4
 */
@Data
public class PageResult<T> {
    private Long total;
    private List<T> rows;

    public PageResult(long total, List<T> records) {
        this.total = total;
        this.rows = records;
    }
}
