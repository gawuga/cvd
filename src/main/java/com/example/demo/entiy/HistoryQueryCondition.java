package com.example.demo.entiy;

import lombok.Data;

/**
 * 历史数据查询条件类
 */
@Data
public class HistoryQueryCondition extends BaseQueryCondition{


//当前页
private int currentPage;

//页的大小
private int pageSize;

private String year;
private String month;
}
