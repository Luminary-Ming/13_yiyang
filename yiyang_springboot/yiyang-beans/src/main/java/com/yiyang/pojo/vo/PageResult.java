package com.yiyang.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    // 查询总记录数
    private Long total;
    // 查询当前页面显示的数据
    private T data;
}
