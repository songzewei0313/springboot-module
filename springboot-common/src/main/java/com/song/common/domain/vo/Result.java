package com.song.common.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author leijie 统一返回
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    /**
     * 状态码：
     * 10000 一切正常
     * 10001 系统出错
     * 10002 Token校验失败
     * 10003 操作失败
     * 10004 服务调用错误
     */
    private Integer code;

    /**
     * 返回的提示信息
     */
    private String msg;

    private Boolean success;

    /**
     * 返回的数据
     */
    private Object data;

    /**
     * 返回数量
     */
    private Long count;

}

