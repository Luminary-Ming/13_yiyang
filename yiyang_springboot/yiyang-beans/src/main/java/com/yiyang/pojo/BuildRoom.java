package com.yiyang.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 楼宇和房间的中间表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("build_room")
@Schema(name = "BuildRoom", description = "楼宇与房间的关系的实体类")
public class BuildRoom {

    @Schema(name = "id", description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(name = "bid", description = "楼宇ID")
    private Integer bid;

    @Schema(name = "rid", description = "房间ID")
    private Integer rid;

    @Schema(name = "type", description = "房间户型")
    private String type;

    @Schema(name = "direction", description = "房间朝向")
    private String direction;

    @Schema(name = "imgUrl", description = "房间图片地址")
    @TableField("img_url")
    private String imgUrl;

    @Schema(name = "description", description = "房间详细描述")
    private String description;

    @Schema(name = "isDelete", description = "否被弃用 1：弃用 0：使用")
    @TableLogic  // 逻辑删除注解
    @TableField("is_delete")
    private Integer isDelete;

}
