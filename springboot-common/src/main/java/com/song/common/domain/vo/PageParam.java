package com.song.common.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author leijie  分页参数
 */
@Data
public class PageParam {
    /**
     * 每页显示数量
     */
    @ApiModelProperty(value = "每页显示数量")
    private Integer pageSize = 10;

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码")
    private Integer pageNum = 1;

    /**
     * 页码
     */
    @ApiModelProperty(value = "是否查询数量")
    private Boolean searchCount = true;
}
