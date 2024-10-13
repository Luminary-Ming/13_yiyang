package com.yiyang.pojo.vo;

import com.yiyang.pojo.Building;
import lombok.Data;

/**
 * roomCount 该属性相当于楼宇表外加字段，表示每个楼宇有多少个房间
 */
@Data
public class BuildingVO extends Building {
    // 房间数
    private Long roomCount;
}
