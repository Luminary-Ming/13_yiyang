package com.yiyang.pojo.vo;

import com.yiyang.utils.BizCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

/**
 * vo 包下的类通常包含与前端界面交互所需的数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Result", description = "统一结果集对象")

public class Result<T> implements Serializable {
    /*
        JDK14 以后 Serializable 类被鼓励使用 @Serial 注释来帮助编译器捕获错误声明的与序列化相关的字段和方法，
        否则可能难以检测到的错误声明。
     */
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "code", description = "响应的状态码")
    private Integer code;
    @Schema(name = "message", description = "响应状态码的说明信息")
    private String message;
    @Schema(name = "data", description = "响应给客户端的数据")
    private T data;

    /*-------------------- 返回成功 ---------------------------------*/
    public static <T> Result<T> success() {
        return success(BizCode.SUCCESS, null);
    }

    public static <T> Result<T> success(T data) {
        return success(BizCode.SUCCESS, data);
    }

    public static <T> Result<T> success(BizCode bizCode) {
        return new Result<>(bizCode.getCode(), bizCode.getMsg(), null);
    }

    public static <T> Result<T> success(BizCode bizCode, T data) {
        return new Result<>(bizCode.getCode(), bizCode.getMsg(), data);
    }

    /*-------------------- 返回失败 ---------------------------------*/
    public static <T> Result<T> fail() {
        return fail(BizCode.FAIL);
    }

    public static <T> Result<T> fail(BizCode bizCode) {
        return new Result<>(bizCode.getCode(), bizCode.getMsg(), null);
    }

    public static <T> Result<T> fail(BizCode bizCode, T data) {
        return new Result<>(bizCode.getCode(), bizCode.getMsg(), data);
    }

    public static <T> Result<T> fail(Integer code, String message) {
        return new Result<>(code, message, null);
    }
}
