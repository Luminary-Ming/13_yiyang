package com.yiyang.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class BuildFloorVO {
    private String floor;
    private List<BuildRoomVO> buildRoomVOList;
}
