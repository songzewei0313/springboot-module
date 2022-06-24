package com.song.springbootredis.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.song.common.domain.entity.Users;

/**
 * <p>
 * 用户管理  服务类
 * </p>
 *
 * @author songzw
 * @since 2022-05-05
 */
public interface UsersService extends IService<Users> {

    Users queryById(String id);
}
