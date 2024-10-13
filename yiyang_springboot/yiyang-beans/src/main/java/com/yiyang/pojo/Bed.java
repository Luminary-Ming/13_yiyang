package com.yiyang.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 床位表
 */
@Data
@TableName("bed")
@Schema(name = "Bed", description = "床位信息")
public class Bed {

    @Schema(name = "id", description = "床位ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(name = "num", description = "床位号")
    private String num;

    @Schema(name = "brid", description = "楼宇与房间ID")
    private Integer brid;

    @Schema(name = "status", description = "床位状态 （0：占用 1：未占用 2：外出）")
    private Integer status;

    @Schema(name = "isDelete", description = "否被弃用 1：弃用 0：使用")
    @TableLogic  // 逻辑删除注解
    @TableField("is_delete")
    private Integer isDelete;
}
