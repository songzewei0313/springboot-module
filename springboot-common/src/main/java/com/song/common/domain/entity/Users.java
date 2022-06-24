package com.song.common.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 用户管理 
 * </p>
 *
 * @author songzw
 * @since 2022-05-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Users对象", description="用户管理 ")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建人")
    private String createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private String updateTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新人")
    private String updateUser;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "登录账号 不可重复")
    private String userName;

    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "状态 1-启用，0-未启用")
    private Integer state;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否在线 0-不在线，1-在线")
    private Integer isOnline;

    @ApiModelProperty(value = "邮箱地址")
    private String email;

    @ApiModelProperty(value = "联系方式 手机号")
    private String phone;

    private String ip;
}
