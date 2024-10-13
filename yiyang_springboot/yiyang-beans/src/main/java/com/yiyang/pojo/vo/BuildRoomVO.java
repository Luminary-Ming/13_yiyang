package com.yiyang.pojo.vo;

import com.yiyang.pojo.BuildRoom;
import lombok.Data;

/**
 * 用于渲染 build_room 表中的楼宇id、房间id对应的楼宇名称、房间名称
 */
@Data
public class BuildRoomVO extends BuildRoom {
    // 楼宇名称
    private String buildingName;
    // 房间号
    private String rooNum;
    // 房间所在楼层
    private String floor;
}
