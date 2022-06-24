package com.song.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.song.common.utils.DateUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author song 自动填充 如：@TableField(fill=FieldFill.INSERT)
 */
@Component
public class MetaObjectHandlerConfig implements MetaObjectHandler {

    /**
     * @author leijie
     * 新增记录时
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //获取当前用户信息
        String username;
        username = "song";
        //创建时间默认当前时间
        setFieldValByName("createTime", DateUtil.yyyy_MM_dd_HH_mm_ss(), metaObject);
        setFieldValByName("lastuppwdtime", DateUtil.yyyy_MM_dd_HH_mm_ss(), metaObject);
        setFieldValByName("updateTime", DateUtil.yyyy_MM_dd_HH_mm_ss(), metaObject);
        setFieldValByName("createUser", username, metaObject);
        setFieldValByName("updateUser", username, metaObject);
    }

    /**
     * @author leijie
     * 修改记录时
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        //获取当前用户信息

        String username;

        username = "song";

        //修改时间
        setFieldValByName("updateTime", DateUtil.yyyy_MM_dd_HH_mm_ss(), metaObject);
        setFieldValByName("updateUser", username, metaObject);
    }

}
