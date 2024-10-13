package com.yiyang.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 楼宇表
 */
@Data
@TableName("building")
@Schema(name = "Building", description = "楼栋信息")
public class Building {

    @Schema(name = "id", description = "楼宇ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(name = "name", description = "楼宇名称")
    private String name;

    @Schema(name = "height", description = "层数")
    private Integer height;

    @Schema(name = "color", description = "楼宇颜色")
    private String color;

    @Schema(name = "isDelete", description = "否被弃用 1：弃用 0：使用")
    @TableLogic  // 逻辑删除注解
    @TableField("is_delete")
    private Integer isDelete;

    @Schema(name = "createTime", description = "创建时间")
    @TableField("create_time")
    private Date createTime;
}
