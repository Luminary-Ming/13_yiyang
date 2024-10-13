package com.yiyang.utils;

import lombok.Data;
import lombok.Getter;

/**
 * 状态码类
 */
@Getter
public enum BizCode {
    UNKNOWN(0, "未知错误"),
    SUCCESS(2000,"操作成功"),

    FAIL(5000,"操作失败"),
    NO_DATA(5001,"没有查询到数据"),
    FILE_UPLOAD_ERROR(5002,"文件上传失败"),
    DATA_VALID_ERROR(5003,"不是有效数据"),

    ADD_BUILDING_ROOM_ERROR(6000,"添加房间与楼宇关系失败"),
    UPDATE_BUILDING_ROOM_ERROR(6001, "修改房间与楼宇关系失败"),
    DELETE_BUILDING_ROOM_ERROR(6002, "删除房间与楼宇关系失败"),

    USER_NOT_EXIST(1001,"用户不存在"),
    USER_PASSWORD_ERROR(1002,"用户名或密码错误"),
    USER_NOT_LOGIN(1003,"用户未登录"),
    USER_NOT_AUTHORIZED(1004,"用户未授权"),
    USER_NOT_PERMISSION(1005,"用户无权限");

    private final Integer code;
    private final String msg;

    BizCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
