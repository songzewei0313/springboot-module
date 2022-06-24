package com.song.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.song.common.domain.vo.Result;

/**
 * @author song
 */
public class ResultUtil {
    /**
     * 一切正常
     */
    public static int successes_code = 200;
    /**
     * 操作失败
     */
    public static int error_code = 500;
    /**
     * 系统出错
     */
    public static int sys_error_code = 500;
    /**
     * Token校验失败
     */
    public static int token_error_code = 10002;
    /**
     * 关联服务报错
     */
    public static int client_error_code = 10004;
    /**
     * token超过有效期
     */
    public static int tole_has_expired = 10005;
    /**
     * 状态码：
     * 10000 一切正常
     * 10001 系统出错
     * 10002 Token校验失败
     * 10003 操作失败
     * 10004 关联服务报错
     */

    /**
     * 一般用于新增、修改、删除返回
     *
     * @param flag 成功 or 失败
     * @return
     */
    public static Result res(boolean flag) {
        Result result = new Result();
        if (flag) {
            result.setSuccess(true);
            result.setCode(successes_code);
            result.setMsg("");
        } else {
            result.setSuccess(false);
            result.setCode(error_code);
            result.setMsg("操作失败");
        }
        return result;
    }

    /**
     * 一般用于表格返回
     *
     * @param page
     * @return
     */
    public static Result res(IPage page) {
        Result result = new Result();
        result.setCode(successes_code);
        result.setMsg("");
        result.setData(page.getRecords());
        result.setCount(page.getTotal());
        return result;
    }

    /**
     * 返回其他内容
     *
     * @param data
     * @return
     */
    public static Result res(Object data) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(successes_code);
        result.setData(data);
        return result;
    }

    /**
     * 返回手动查询的表格
     *
     * @param data  数据
     * @param count 总数
     * @return Result
     * @author sunyf
     * @date 2021-03-13
     */
    public static Result res(Object data, long count) {
        Result result = new Result();
        result.setCode(successes_code);
        result.setData(data);
        result.setCount(count);
        return result;
    }

    public static Result errorClient(String errorMsg) {
        Result result = new Result();
        result.setCode(client_error_code);
        result.setData(errorMsg);
        return result;
    }

    public static Result error(String errorMsg) {
        Result result = new Result();
        result.setCode(error_code);
        result.setSuccess(false);
        result.setMsg(errorMsg);
        return result;
    }

    public static Result error(int code, String errorMsg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(errorMsg);
        return result;
    }

    public static Result error(Object data, String errorMsg) {
        Result result = new Result();
        result.setCode(error_code);
        result.setData(data);
        result.setMsg(errorMsg);
        return result;
    }

    /**
     * 成功
     *
     * @return
     */
    public static Result success() {
        return res(true);
    }
}
