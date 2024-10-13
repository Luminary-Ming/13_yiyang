package com.yiyang.exception;

import com.yiyang.utils.BizCode;
import lombok.Getter;

/**
 * 自定义异常类
 */
@Getter
public class BizException extends RuntimeException{
    private final Integer code;
    private final String msg;

    public BizException(Integer code,String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BizException(BizCode bizCode){
        super(bizCode.getMsg());
        this.code = bizCode.getCode();
        this.msg = bizCode.getMsg();
    }
}
