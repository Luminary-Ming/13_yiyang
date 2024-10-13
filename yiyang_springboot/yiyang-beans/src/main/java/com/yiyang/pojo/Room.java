package com.yiyang.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 房间表
 */
@Data
@TableName("room")
@Schema(name = "Building" , description = "房间信息")
public class Room {

    @Schema(name = "id", description = "房间ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(name = "num", description = "房间号")
    private String num;

    @Schema(name = "floor", description = "所属楼层")
    private String floor;

    @Schema(name = "isDelete", description = "否被弃用 1：弃用 0：使用")
    @TableLogic  // 逻辑删除注解
    @TableField("is_delete")
    private Integer isDelete;
}
